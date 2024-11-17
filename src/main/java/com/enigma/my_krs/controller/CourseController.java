package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
import com.enigma.my_krs.dto.request.CourseRequest;
import com.enigma.my_krs.dto.response.CourseResponse;
import com.enigma.my_krs.service.CourseService;
import com.enigma.my_krs.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.COURSE_API)
@RequiredArgsConstructor
@Tag(name = "Course", description = "Course Management APIs")
public class CourseController {
    private final CourseService courseService;

    @Operation(summary = "Create a new course", description = "Creates a new course with the provided course details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        CourseResponse course = courseService.createCourse(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_COURSE, course);
    }

    @Operation(summary = "Get all courses", description = "Retrieves a list of all available courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all courses",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllCourses() {
        List<CourseResponse> courseResponse = courseService.getAllCourses();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_COURSE, courseResponse);
    }

    @Operation(summary = "Get course by ID", description = "Retrieves a specific course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the course",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable("courseId") UUID id) {
        CourseResponse courseResponse = courseService.getCourseById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_COURSE_BY_ID, courseResponse);
    }

    @Operation(summary = "Update course by ID", description = "Updates an existing course with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course updated successfully",
                    content = @Content(schema = @Schema(implementation = CourseResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(path = "/{courseId}")
    public ResponseEntity<?> updateCourseById(@PathVariable("courseId") UUID id, @RequestBody CourseRequest request) {
        CourseResponse courseResponse = courseService.updateCourseById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_COURSE_BY_ID, courseResponse);
    }

    @Operation(summary = "Delete course by ID", description = "Deletes a specific course by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "/{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("courseId") UUID id) {
        courseService.deleteCourseById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_COURSE_BY_ID, null);
    }
}