package com.enigma.my_krs.service.impl;

import com.enigma.my_krs.dto.request.CourseOfferingRequest;
import com.enigma.my_krs.dto.request.SearchRequest;
import com.enigma.my_krs.dto.response.CourseOfferingResponse;
import com.enigma.my_krs.entity.CourseOffering;
import com.enigma.my_krs.repository.CourseOfferingRepository;
import com.enigma.my_krs.service.CourseOfferingService;
import com.enigma.my_krs.service.CourseService;
import com.enigma.my_krs.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseOfferingServiceImpl implements CourseOfferingService {
    private final CourseOfferingRepository courseOfferingRepository;
    private final CourseService courseService;
    private final LecturerService lecturerService;

    @Override
    public CourseOfferingResponse createCourseOffering(CourseOfferingRequest request) {
        CourseOffering courseOffering = CourseOffering.builder()
                .id(UUID.randomUUID())
                .course(courseService.getOne(request.getCourse()))
                .classRoom(request.getClassRoom())
                .availableSeats(request.getAvailableSeats())
                .schedule(request.getSchedule())
                .lecturer(lecturerService.getOne(request.getLecturer()))
                .build();
        courseOfferingRepository.insertCourseOffering(
                courseOffering.getId(),
                courseOffering.getCourse().getId(),
                courseOffering.getClassRoom(),
                courseOffering.getAvailableSeats(),
                courseOffering.getSchedule(),
                courseOffering.getLecturer().getId()
        );
        return mapToCourseOfferingResponse(courseOffering);
    }

    @Override
    public Page<CourseOfferingResponse> getAllCourseOfferings(SearchRequest request) {
        int page = request.getPage();
        int size = request.getSize();
        String query = request.getQuery();
        String sortBy = request.getSortBy() != null ? request.getSortBy() : "id";
        String sortDirection = request.getSortDirection().equalsIgnoreCase("ASC") ? "ASC" : "DESC";
        int offset = page * size;

        List<CourseOffering> courseOfferings = courseOfferingRepository.findAllCourseOfferings(
                query, sortBy, sortDirection, size, offset
        );

        Long totalCourseOfferings = courseOfferingRepository.countAllCourseOfferings(query);

        // Map results to response objects
        List<CourseOfferingResponse> responses = courseOfferings.stream()
                .map(this::mapToCourseOfferingResponse)
                .toList();

        return new PageImpl<>(responses, PageRequest.of(page, size), totalCourseOfferings);
    }

    @Override
    public CourseOffering getOne(UUID id) {
        return courseOfferingRepository.findCourseOfferingById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course offering not found"));
    }

    @Override
    public CourseOfferingResponse getCourseOfferingById(UUID id) {
        return mapToCourseOfferingResponse(getOne(id));
    }

    @Override
    public CourseOfferingResponse updateCourseOfferingById(UUID id, CourseOfferingRequest request) {
        CourseOffering courseOffering = getOne(id);
        courseOffering.setCourse(courseService.getOne(request.getCourse()));
        courseOffering.setSchedule(request.getSchedule());
        courseOffering.setLecturer(lecturerService.getOne(request.getLecturer()));
        courseOffering.setClassRoom(request.getClassRoom());
        courseOffering.setAvailableSeats(request.getAvailableSeats());
        courseOfferingRepository.updateCourseOffering(courseOffering.getId(), courseOffering.getCourse().getId(), courseOffering.getClassRoom(), courseOffering.getAvailableSeats(), courseOffering.getSchedule(), courseOffering.getLecturer().getId());
        return mapToCourseOfferingResponse(courseOffering);
    }

    @Override
    public void deleteCourseOfferingById(UUID id) {
        courseOfferingRepository.deleteCourseOffering(getOne(id).getId());
    }

    private CourseOfferingResponse mapToCourseOfferingResponse(CourseOffering courseOffering) {
        return CourseOfferingResponse.builder()
                .id(courseOffering.getId())
                .course(courseOffering.getCourse().getId())
                .classRoom(courseOffering.getClassRoom())
                .availableSeats(courseOffering.getAvailableSeats())
                .schedule(courseOffering.getSchedule())
                .lecturer(courseOffering.getLecturer().getId())
                .build();
    }
}
