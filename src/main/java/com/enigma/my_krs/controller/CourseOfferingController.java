package com.enigma.my_krs.controller;

import com.enigma.my_krs.dto.request.CourseOfferingRequest;
import com.enigma.my_krs.dto.request.SearchRequest;
import com.enigma.my_krs.dto.response.CourseOfferingResponse;
import com.enigma.my_krs.service.CourseOfferingService;
import com.enigma.my_krs.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/course-offerings")
@RequiredArgsConstructor
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<?> createCourseOffering(@RequestBody CourseOfferingRequest request) {
        CourseOfferingResponse courseOffering = courseOfferingService.createCourseOffering(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Course offering created successsfully", courseOffering);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourseOfferings(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        SearchRequest pagingAndSortingRequest = SearchRequest.builder()
                .query(query)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();
        Page<CourseOfferingResponse> courseOfferings = courseOfferingService.getAllCourseOfferings(pagingAndSortingRequest);
        return ResponseUtil.buildResponsePage(HttpStatus.OK, "Fetch All course offering successfully", courseOfferings);
    }

    @GetMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> getCourseOfferingById(@PathVariable("courseOfferingId") UUID id) {
        CourseOfferingResponse courseOfferingById = courseOfferingService.getCourseOfferingById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Fetch course offering by id successfully", courseOfferingById);
    }

    @PutMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> updateCourseOfferingById(@PathVariable("courseOfferingId") UUID id, @RequestBody CourseOfferingRequest request) {
        CourseOfferingResponse courseOfferingResponse = courseOfferingService.updateCourseOfferingById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Updated course offering by id successfully", courseOfferingResponse);
    }

    @DeleteMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> deleteCourseOfferingById(@PathVariable("courseOfferingId") UUID id) {
        courseOfferingService.deleteCourseOfferingById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Delete course offering by id successfully", null);
    }
}
