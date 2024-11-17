package com.enigma.my_krs.repository;

import com.enigma.my_krs.entity.EnrollmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO t_enrollment_detail (id, enrollment_id, course_offering_id) VALUES (:id, :enrollment_id, :course_offering_id)", nativeQuery = true)
    void insertEnrollmentDetail(@Param("id") UUID id,
                                @Param("enrollment_id") UUID enrollmentId,
                                @Param("course_offering_id") UUID courseOffering);

    @Query(value = "SELECT * FROM t_enrollment_detail", nativeQuery = true)
    List<EnrollmentDetail> findAllEnrollments();

    @Query(value = "SELECT * FROM t_enrollment_detail WHERE id = :id", nativeQuery = true)
    Optional<EnrollmentDetail> findEnrollmentDetailById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM t_enrollment_detail WHERE id = :id", nativeQuery = true)
    void deleteEnrollmentDetailById(@Param("id") UUID id);
}
