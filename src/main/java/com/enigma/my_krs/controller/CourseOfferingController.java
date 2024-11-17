package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
import com.enigma.my_krs.dto.request.CourseOfferingRequest;
import com.enigma.my_krs.dto.request.SearchRequest;
import com.enigma.my_krs.dto.response.CourseOfferingResponse;
import com.enigma.my_krs.service.CourseOfferingService;
import com.enigma.my_krs.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(Constant.COURSE_OFFERING_API)
@RequiredArgsConstructor
@Tag(name = "Course Offering", description = "Course Offering Management APIs")
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    @Operation(summary = "Create a new course offering", description = "Creates a new course offering with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course offering created successfully",
                    content = @Content(schema = @Schema(implementation = CourseOfferingResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createCourseOffering(@RequestBody CourseOfferingRequest request) {
        CourseOfferingResponse courseOffering = courseOfferingService.createCourseOffering(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_COURSE_OFFERING, courseOffering);
    }

    @Operation(summary = "Get all course offerings", description = "Retrieves a paginated list of course offerings with optional filtering and sorting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved course offerings",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllCourseOfferings(
            @Parameter(description = "Search query string")
            @RequestParam(required = false) String query,

            @Parameter(description = "Page number (0-based)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of items per page")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction (ASC or DESC)")
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

    @Operation(summary = "Get course offering by ID", description = "Retrieves a specific course offering by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the course offering",
                    content = @Content(schema = @Schema(implementation = CourseOfferingResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course offering not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> getCourseOfferingById(
            @Parameter(description = "ID of the course offering to retrieve")
            @PathVariable("courseOfferingId") UUID id) {
        CourseOfferingResponse courseOfferingById = courseOfferingService.getCourseOfferingById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_COURSE_OFFERING_BY_ID, courseOfferingById);
    }

    @Operation(summary = "Update course offering by ID", description = "Updates an existing course offering with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course offering updated successfully",
                    content = @Content(schema = @Schema(implementation = CourseOfferingResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course offering not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> updateCourseOfferingById(
            @Parameter(description = "ID of the course offering to update")
            @PathVariable("courseOfferingId") UUID id,
            @RequestBody CourseOfferingRequest request) {
        CourseOfferingResponse courseOfferingResponse = courseOfferingService.updateCourseOfferingById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_COURSE_OFFERING_BY_ID, courseOfferingResponse);
    }

    @Operation(summary = "Delete course offering by ID", description = "Deletes a specific course offering by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course offering deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Course offering not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "/{courseOfferingId}")
    public ResponseEntity<?> deleteCourseOfferingById(
            @Parameter(description = "ID of the course offering to delete")
            @PathVariable("courseOfferingId") UUID id) {
        courseOfferingService.deleteCourseOfferingById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_COURSE_OFFERING_BY_ID, null);
    }
}