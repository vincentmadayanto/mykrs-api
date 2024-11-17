package com.enigma.my_krs.service.impl;

import com.enigma.my_krs.dto.response.EnrollmentDetailResponse;
import com.enigma.my_krs.dto.response.EnrollmentResponse;
import com.enigma.my_krs.entity.CourseOffering;
import com.enigma.my_krs.entity.Enrollment;
import com.enigma.my_krs.entity.EnrollmentDetail;
import com.enigma.my_krs.repository.EnrollmentDetailRepository;
import com.enigma.my_krs.repository.EnrollmentRepository;
import com.enigma.my_krs.service.CourseOfferingService;
import com.enigma.my_krs.service.EnrollmentService;
import com.enigma.my_krs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final StudentService studentService;
    private final CourseOfferingService courseOfferingService;

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
        return enrollmentRepository.findEnrollmentById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment Id Not Found"));
    }

    @Override
    public EnrollmentResponse getEnrollmentById(UUID id) {
        return mapToEnrollmentResponse(getOne(id));
    }

    @Override
    public EnrollmentResponse getEnrollmentByStudentId(UUID studentId) {
        return mapToEnrollmentResponse(enrollmentRepository.findEnrollmentByStudentId(studentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment Id Not Found")));
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAllEnrollments().stream().map(this::mapToEnrollmentResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteEnrollmentById(UUID id) {
        enrollmentRepository.deleteEnrollment(getOne(id).getId());
    }

    @Override
    public EnrollmentDetail getOneEnrollmentDetail(UUID id) {
        return enrollmentDetailRepository.findEnrollmentDetailById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Enrollment Detail Not Found"));
    }

    // Enrollment Detail
    @Override
    public EnrollmentResponse addCourseOfferingToEnrollment(UUID enrollmentId, UUID courseOfferingId) {
        enrollmentDetailRepository.insertEnrollmentDetail(UUID.randomUUID(), enrollmentId, courseOfferingId);

        Enrollment enrollment = getOne(enrollmentId);
        CourseOffering courseOffering = courseOfferingService.getOne(courseOfferingId);
        enrollment.setTotalQuota(enrollment.getTotalQuota() + courseOffering.getCourse().getCredits());
        enrollment.setRemainingQuota(enrollment.getRemainingQuota() - courseOffering.getCourse().getCredits());
        enrollmentRepository.updateTotalQuota(enrollmentId, enrollment.getTotalQuota(), enrollment.getRemainingQuota());

        return mapToEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentResponse removeCourseOfferingFromEnrollment(UUID enrollmentDetailId) {
        EnrollmentDetail enrollmentDetail = getOneEnrollmentDetail(enrollmentDetailId);
        Enrollment enrollment = getOne(enrollmentDetail.getEnrollment().getId());
        CourseOffering courseOffering = courseOfferingService.getOne(enrollmentDetail.getCourseOffering().getId());
        enrollment.setTotalQuota(enrollment.getTotalQuota() - courseOffering.getCourse().getCredits());
        enrollment.setRemainingQuota(enrollment.getRemainingQuota() + courseOffering.getCourse().getCredits());
        enrollmentRepository.updateTotalQuota(enrollment.getId(), enrollment.getTotalQuota(), enrollment.getRemainingQuota());
        enrollmentDetailRepository.deleteEnrollmentDetailById(enrollmentDetailId);

        return mapToEnrollmentResponse(enrollment);
    }

    @Override
    public List<EnrollmentDetailResponse> getAllEnrollmentDetails() {
        List<EnrollmentDetail> allEnrollments = enrollmentDetailRepository.findAllEnrollments();
        return allEnrollments.stream().map(this::mapToEnrollmentDetailResponse).collect(Collectors.toList());
    }

    private EnrollmentResponse mapToEnrollmentResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .totalQuota(enrollment.getTotalQuota())
                .remainingQuota(enrollment.getRemainingQuota())
                .build();
    }

    private EnrollmentDetailResponse mapToEnrollmentDetailResponse(EnrollmentDetail enrollmentDetail) {
        return EnrollmentDetailResponse.builder()
                .id(enrollmentDetail.getId())
                .enrollmentId(enrollmentDetail.getEnrollment().getId())
                .courseOfferingId(enrollmentDetail.getCourseOffering().getId())
                .build();
    }
}
