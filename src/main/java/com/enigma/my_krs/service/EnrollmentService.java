package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.response.EnrollmentResponse;
import com.enigma.my_krs.entity.Enrollment;

import java.util.List;
import java.util.UUID;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(UUID studentId);
    Enrollment getOne(UUID id);
    EnrollmentResponse getEnrollmentById(UUID id);
    EnrollmentResponse getEnrollmentByStudentId(UUID studentId);
    List<EnrollmentResponse> getAllEnrollments();
    EnrollmentResponse updateEnrollmentById(UUID id);
    void deleteEnrollmentById(UUID id);

    // Enrollment Detail Service
}
