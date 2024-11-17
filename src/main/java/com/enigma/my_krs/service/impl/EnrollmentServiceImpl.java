package com.enigma.my_krs.service.impl;

import com.enigma.my_krs.dto.response.EnrollmentResponse;
import com.enigma.my_krs.entity.Enrollment;
import com.enigma.my_krs.repository.EnrollmentRepository;
import com.enigma.my_krs.service.EnrollmentService;
import com.enigma.my_krs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;

    @Override
    public EnrollmentResponse createEnrollment(UUID studentId) {
        Enrollment enrollment = Enrollment.builder()
                .id(UUID.randomUUID())
                .student(studentService.getOne(studentId))
                .totalQuota(0)
                .remainingQuota(24)
                .build();
        enrollmentRepository.insertEnrollment(enrollment.getId(), enrollment.getStudent().getId(), enrollment.getTotalQuota(), enrollment.getRemainingQuota());
        return mapToEnrollmentResponse(enrollment);
    }

    @Override
    public Enrollment getOne(UUID id) {
        return null;
    }

    @Override
    public EnrollmentResponse getEnrollmentById(UUID id) {
        return null;
    }

    @Override
    public EnrollmentResponse getEnrollmentByStudentId(UUID studentId) {
        return null;
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return null;
    }

    @Override
    public EnrollmentResponse updateEnrollmentById(UUID id) {
        return null;
    }

    @Override
    public void deleteEnrollmentById(UUID id) {

    }

    private EnrollmentResponse mapToEnrollmentResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .totalQuota(enrollment.getTotalQuota())
                .remainingQuota(enrollment.getRemainingQuota())
                .build();
    }
}
