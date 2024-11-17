package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.response.EnrollmentDetailResponse;
import com.enigma.my_krs.dto.response.EnrollmentResponse;
import com.enigma.my_krs.entity.Enrollment;
import com.enigma.my_krs.entity.EnrollmentDetail;

import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(UUID studentId);
    Enrollment getOne(UUID id);
    EnrollmentResponse getEnrollmentById(UUID id);
    EnrollmentResponse getEnrollmentByStudentId(UUID studentId);
    List<EnrollmentResponse> getAllEnrollments();
    void deleteEnrollmentById(UUID id);

    // Enrollment Detail Service
    EnrollmentDetail getOneEnrollmentDetail(UUID id);
    EnrollmentResponse addCourseOfferingToEnrollment(UUID enrollmentId, UUID courseOfferingId);
    EnrollmentResponse removeCourseOfferingFromEnrollment(UUID enrollmentDetailId);
    List<EnrollmentDetailResponse> getAllEnrollmentDetails();
}
