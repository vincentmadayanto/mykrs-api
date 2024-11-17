package com.enigma.my_krs.controller;

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
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody EnrollmentRequest request) {
        EnrollmentResponse enrollment = enrollmentService.createEnrollment(request.getStudentId());
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Enrollment created successfully", enrollment);
    }

    @GetMapping
    public ResponseEntity<?> getAllEnrollments() {
        List<EnrollmentResponse> allEnrollments = enrollmentService.getAllEnrollments();
        return ResponseUtil.buildResponse(HttpStatus.OK, "Fetch all enrollment successfully", allEnrollments);
    }

    @GetMapping(path = "/{enrollmentId}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable("enrollmentId") UUID enrollmentId) {
        EnrollmentResponse enrollmentById = enrollmentService.getEnrollmentById(enrollmentId);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Fetch enrollment by id successfully", enrollmentById);
    }

//    @GetMapping(path = "/{studentId}")
//    public ResponseEntity<?> getEnrollmentByStudentId(@PathVariable("studentId") UUID studentId) {
//        EnrollmentResponse enrollmentByStudentId = enrollmentService.getEnrollmentByStudentId(studentId);
//        return ResponseUtil.buildResponse(HttpStatus.OK, "Fetch enrollment by student id successfully", enrollmentByStudentId);
//    }

    @DeleteMapping(path = "/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollmentById(@PathVariable("enrollmentId") UUID enrollmentId) {
        enrollmentService.deleteEnrollmentById(enrollmentId);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Delete enrollment by student id successfully", null);
    }

    // Enrollment Detail
    @PostMapping(path = "/{enrollmentId}/add-course")
    public ResponseEntity<?> addCourseOfferingToEnrollment(
            @PathVariable("enrollmentId") UUID enrollmentId,
            @RequestParam UUID courseOfferingId
    ) {
        EnrollmentResponse enrollmentResponse = enrollmentService.addCourseOfferingToEnrollment(enrollmentId, courseOfferingId);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Added Enrollment Detail into Enrollment Successfully", enrollmentResponse);
    }

    @DeleteMapping(path = "/details/{enrollmentDetailId}")
    public ResponseEntity<?> removeCourseOfferingFromEnrollment(@PathVariable("enrollmentDetailId") UUID enrollmentDetailId) {
        enrollmentService.removeCourseOfferingFromEnrollment(enrollmentDetailId);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Deleted Enrollment Detail from Enrollment", null);
    }

    @GetMapping(path = "/details")
    public ResponseEntity<?> getAllEnrollmentDetails() {
        List<EnrollmentDetailResponse> allEnrollmentDetails = enrollmentService.getAllEnrollmentDetails();
        return ResponseUtil.buildResponse(HttpStatus.OK, "Fetch Enrollment details from Enrollment succcess", allEnrollmentDetails);
    }
}
