package com.enigma.my_krs.service.impl;

import com.enigma.my_krs.dto.request.CourseRequest;
import com.enigma.my_krs.dto.response.CourseResponse;
import com.enigma.my_krs.entity.Course;
import com.enigma.my_krs.repository.CourseRepository;
import com.enigma.my_krs.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseResponse createCourse(CourseRequest request) {
        Course course = Course.builder()
                .id(UUID.randomUUID())
                .courseCode(request.getCourseCode())
                .name(request.getName())
                .credits(request.getCredits())
                .build();
        courseRepository.insertCourse(
                course.getId(),
                course.getCourseCode(),
                course.getName(),
                course.getCredits()
        );
        return mapToCourseResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllCourses().stream().map(this::mapToCourseResponse).collect(Collectors.toList());
    }

    @Override
    public Course getOne(UUID id) {
        return courseRepository.findCourseById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found"));
    }

    @Override
    public CourseResponse getCourseById(UUID id) {
        return mapToCourseResponse(getOne(id));
    }

    @Override
    public CourseResponse updateCourseById(UUID id, CourseRequest request) {
        Course course = getOne(id);
        course.setCourseCode(request.getCourseCode());
        course.setName(request.getName());
        course.setCredits(request.getCredits());
        courseRepository.updateCourse(course.getId() ,course.getCourseCode(), course.getName(), course.getCredits());
        return mapToCourseResponse(course);
    }

    @Override
    public void deleteCourseById(UUID id) {
        courseRepository.deleteCourse(getOne(id).getId());
    }

    private CourseResponse mapToCourseResponse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .courseCode(course.getCourseCode())
                .name(course.getName())
                .credits(course.getCredits())
                .build();
    }
}
