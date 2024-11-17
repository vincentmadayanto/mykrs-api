package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
import com.enigma.my_krs.dto.request.EnrollmentRequest;
import com.enigma.my_krs.dto.response.EnrollmentDetailResponse;
import com.enigma.my_krs.dto.response.EnrollmentResponse;
import com.enigma.my_krs.service.EnrollmentService;
import com.enigma.my_krs.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping(Constant.ENROLLMENT_API)
@RequiredArgsConstructor
@Tag(name = "Enrollment", description = "Enrollment Management APIs")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Operation(summary = "Create a new enrollment", description = "Creates a new enrollment for a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enrollment created successfully",
                    content = @Content(schema = @Schema(implementation = EnrollmentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid student ID"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentRequest request) {
        EnrollmentResponse enrollment = enrollmentService.createEnrollment(request.getStudentId());
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_ENROLLMENT, enrollment);
    }

    @Operation(summary = "Get all enrollments", description = "Retrieves a list of all enrollments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all enrollments",
                    content = @Content(schema = @Schema(implementation = EnrollmentResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllEnrollments() {
        List<EnrollmentResponse> allEnrollments = enrollmentService.getAllEnrollments();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_ENROLLMENTS, allEnrollments);
    }

    @Operation(summary = "Get enrollment by ID", description = "Retrieves a specific enrollment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the enrollment",
                    content = @Content(schema = @Schema(implementation = EnrollmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Enrollment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "/{enrollmentId}")
    public ResponseEntity<?> getEnrollmentById(
            @Parameter(description = "ID of the enrollment to retrieve")
            @PathVariable("enrollmentId") UUID enrollmentId) {
        EnrollmentResponse enrollmentById = enrollmentService.getEnrollmentById(enrollmentId);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ENROLLMENT_BY_ID, enrollmentById);
    }

    @Operation(summary = "Delete enrollment by ID", description = "Deletes a specific enrollment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Enrollment not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollmentById(
            @Parameter(description = "ID of the enrollment to delete")
            @PathVariable("enrollmentId") UUID enrollmentId) {
        enrollmentService.deleteEnrollmentById(enrollmentId);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_ENROLLMENT_BY_ID, null);
    }

    @Operation(summary = "Add course offering to enrollment",
            description = "Adds a course offering to an existing enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course offering added successfully",
                    content = @Content(schema = @Schema(implementation = EnrollmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Enrollment or course offering not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request or course offering already enrolled"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(path = "/{enrollmentId}/add-course")
    public ResponseEntity<?> addCourseOfferingToEnrollment(
            @Parameter(description = "ID of the enrollment")
            @PathVariable("enrollmentId") UUID enrollmentId,
            @Parameter(description = "ID of the course offering to add")
            @RequestParam UUID courseOfferingId
    ) {
        EnrollmentResponse enrollmentResponse = enrollmentService.addCourseOfferingToEnrollment(enrollmentId, courseOfferingId);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_ADD_COURSE_OFFERING, enrollmentResponse);
    }

    @Operation(summary = "Remove course offering from enrollment",
            description = "Removes a course offering from an enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course offering removed successfully",
                    content = @Content(schema = @Schema(implementation = EnrollmentResponse.class))),
            @ApiResponse(responseCode = "404", description = "Enrollment detail not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(path = "/details/{enrollmentDetailId}")
    public ResponseEntity<?> removeCourseOfferingFromEnrollment(
            @Parameter(description = "ID of the enrollment detail to remove")
            @PathVariable("enrollmentDetailId") UUID enrollmentDetailId) {
        EnrollmentResponse enrollmentResponse = enrollmentService.removeCourseOfferingFromEnrollment(enrollmentDetailId);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_REMOVE_COURSE_OFFERING, enrollmentResponse);
    }

    @Operation(summary = "Get all enrollment details",
            description = "Retrieves a list of all enrollment details including course offerings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all enrollment details",
                    content = @Content(schema = @Schema(implementation = EnrollmentDetailResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "/details")
    public ResponseEntity<?> getAllEnrollmentDetails() {
        List<EnrollmentDetailResponse> allEnrollmentDetails = enrollmentService.getAllEnrollmentDetails();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_ENROLLMENT_DETAILS, allEnrollmentDetails);
    }
}