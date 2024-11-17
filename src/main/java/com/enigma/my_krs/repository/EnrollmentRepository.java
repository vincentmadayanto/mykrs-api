package com.enigma.my_krs.repository;

import com.enigma.my_krs.entity.Enrollment;
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
public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO t_enrollment (id, student_id, total_quota, remaining_quota) VALUES (:id, :student_id, :total_quota, :remaining_quota)", nativeQuery = true)
    void insertEnrollment(@Param("id") UUID id,
                          @Param("student_id") UUID studentId,
                          @Param("total_quota") Integer totalQuota,
                          @Param("remaining_quota") Integer remainingQuota);

    @Query(value = "SELECT * FROM t_enrollment WHERE id = :id", nativeQuery = true)
    Optional<Enrollment> findEnrollmentById(@Param("id") UUID id);

    @Query(value = "SELECT * FROM t_enrollment", nativeQuery = true)
    List<Enrollment> findAllEnrollments();

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_enrollment " +
            "SET student_id = :student_id," +
            "total_quota = :total_quota," +
            "remaining_quota = :remaining_quota " +
            "WHERE id = :id", nativeQuery = true)
    void updateEnrollment(@Param("id") UUID id,
                          @Param("student_id") UUID studentId,
                          @Param("total_quota") Integer totalQuota,
                          @Param("remaining_quota") Integer remainingQuota);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM t_enrollment WHERE id = :id", nativeQuery = true)
    void deleteEnrollment(@Param("id") UUID id);
}
