package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
import com.enigma.my_krs.dto.request.EnrollmentRequest;
import com.enigma.my_krs.dto.response.EnrollmentDetailResponse;
import com.enigma.my_krs.dto.response.EnrollmentResponse;
import com.enigma.my_krs.service.EnrollmentService;
import com.enigma.my_krs.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.ENROLLMENT_API)
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentRequest request) {
        EnrollmentResponse enrollment = enrollmentService.createEnrollment(request.getStudentId());
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_ENROLLMENT, enrollment);
    }

    @GetMapping
    public ResponseEntity<?> getAllEnrollments() {
        List<EnrollmentResponse> allEnrollments = enrollmentService.getAllEnrollments();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_ENROLLMENTS, allEnrollments);
    }

    @GetMapping(path = "/{enrollmentId}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable("enrollmentId") UUID enrollmentId) {
        EnrollmentResponse enrollmentById = enrollmentService.getEnrollmentById(enrollmentId);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ENROLLMENT_BY_ID, enrollmentById);
    }

    @DeleteMapping(path = "/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollmentById(@PathVariable("enrollmentId") UUID enrollmentId) {
        enrollmentService.deleteEnrollmentById(enrollmentId);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_ENROLLMENT_BY_ID, null);
    }

    // Enrollment Detail
    @PostMapping(path = "/{enrollmentId}/add-course")
    public ResponseEntity<?> addCourseOfferingToEnrollment(
            @PathVariable("enrollmentId") UUID enrollmentId,
            @RequestParam UUID courseOfferingId
    ) {
        EnrollmentResponse enrollmentResponse = enrollmentService.addCourseOfferingToEnrollment(enrollmentId, courseOfferingId);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_ADD_COURSE_OFFERING, enrollmentResponse);
    }

    @DeleteMapping(path = "/details/{enrollmentDetailId}")
    public ResponseEntity<?> removeCourseOfferingFromEnrollment(@PathVariable("enrollmentDetailId") UUID enrollmentDetailId) {
        EnrollmentResponse enrollmentResponse = enrollmentService.removeCourseOfferingFromEnrollment(enrollmentDetailId);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_REMOVE_COURSE_OFFERING, enrollmentResponse);
    }

    @GetMapping(path = "/details")
    public ResponseEntity<?> getAllEnrollmentDetails() {
        List<EnrollmentDetailResponse> allEnrollmentDetails = enrollmentService.getAllEnrollmentDetails();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_ENROLLMENT_DETAILS, allEnrollmentDetails);
    }
}
