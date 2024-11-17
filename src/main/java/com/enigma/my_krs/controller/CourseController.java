package com.enigma.my_krs.controller;

import com.enigma.my_krs.dto.request.CourseRequest;
import com.enigma.my_krs.dto.response.CourseResponse;
import com.enigma.my_krs.service.CourseService;
import com.enigma.my_krs.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        CourseResponse course = courseService.createCourse(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Course created successfully", course);
    }

    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        List<CourseResponse> courseResponse = courseService.getAllCourses();
        return ResponseUtil.buildResponse(HttpStatus.OK, "Get All Courses successfully", courseResponse);
    }

    @GetMapping(path = "/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable("courseId") UUID id) {
        CourseResponse courseResponse = courseService.getCourseById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Get Course by id successfully", courseResponse);
    }

    @PutMapping(path = "/{courseId}")
    public ResponseEntity<?> updateCourseById(@PathVariable("courseId") UUID id, @RequestBody CourseRequest request) {
        CourseResponse courseResponse = courseService.updateCourseById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Course updated successfully", courseResponse);
    }

    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("courseId") UUID id) {
        courseService.deleteCourseById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Course deleted successfully", null);
    }
}
