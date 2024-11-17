package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
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
@RequestMapping(Constant.COURSE_OFFERING_API)
@RequiredArgsConstructor
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<?> createCourseOffering(@RequestBody CourseOfferingRequest request) {
        CourseOfferingResponse courseOffering = courseOfferingService.createCourseOffering(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_COURSE_OFFERING, courseOffering);
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
        return ResponseUtil.buildResponsePage(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_COURSE_OFFERING, courseOfferings);
    }

    @GetMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> getCourseOfferingById(@PathVariable("courseOfferingId") UUID id) {
        CourseOfferingResponse courseOfferingById = courseOfferingService.getCourseOfferingById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_COURSE_OFFERING_BY_ID, courseOfferingById);
    }

    @PutMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> updateCourseOfferingById(@PathVariable("courseOfferingId") UUID id, @RequestBody CourseOfferingRequest request) {
        CourseOfferingResponse courseOfferingResponse = courseOfferingService.updateCourseOfferingById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_COURSE_OFFERING_BY_ID, courseOfferingResponse);
    }

    @DeleteMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> deleteCourseOfferingById(@PathVariable("courseOfferingId") UUID id) {
        courseOfferingService.deleteCourseOfferingById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_COURSE_OFFERING_BY_ID, null);
    }
}
