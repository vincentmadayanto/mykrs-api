package com.enigma.my_krs.repository;

import com.enigma.my_krs.entity.CourseOffering;
import org.springframework.data.domain.Page;
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
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO t_course_offering (id, course_id, class, available_seats, schedule, lecturer_id) VALUES (:id, :course_id, :class, :available_seats, :schedule, :lecturer_id)", nativeQuery = true)
    void insertCourseOffering(@Param("id") UUID id,
                              @Param("course_id") UUID courseId,
                              @Param("class") Character classRoom,
                              @Param("available_seats") Integer availableSeats,
                              @Param("schedule") String schedule,
                              @Param("lecturer_id") UUID lecturerId);

    @Query(value = """
                SELECT co.* FROM t_course_offering co
                INNER JOIN m_course c on co.course_id = c.id
                WHERE (LOWER(c.course_code) LIKE LOWER(CONCAT('%', :query, '%')))
                   OR (LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')))
                ORDER BY CASE WHEN :sortDirection = 'ASC' THEN :sortBy END ASC,
                         CASE WHEN :sortDirection = 'DESC' THEN :sortBy END DESC
                LIMIT :size OFFSET :offset
            """, nativeQuery = true)
    List<CourseOffering> findAllCourseOfferings(
            @Param("query") String query,
            @Param("sortBy") String sortBy,
            @Param("sortDirection") String sortDirection,
            @Param("size") int size,
            @Param("offset") int offset
    );

    @Query(
            value = """
            SELECT COUNT(*)
            FROM t_course_offering co
            INNER JOIN m_course c ON co.course_id = c.id
            WHERE (LOWER(c.course_code) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')))
        """,
            nativeQuery = true
    )
    Long countAllCourseOfferings(@Param("query") String query);

    @Query(value = "SELECT * FROM t_course_offering WHERE id = :id", nativeQuery = true)
    Optional<CourseOffering> findCourseOfferingById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_course_offering " +
            "SET course_id = :course_id, " +
            "class = :class, " +
            "available_seats = :available_seats, " +
            "schedule = :schedule, " +
            "lecturer_id = :lecturer_id " +
            "WHERE id = :id", nativeQuery = true)
    void updateCourseOffering(@Param("id") UUID id,
                              @Param("course_id") UUID courseId,
                              @Param("class") Character classRoom,
                              @Param("available_seats") Integer availableSeats,
                              @Param("schedule") String schedule,
                              @Param("lecturer_id") UUID lecturerId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM t_course_offering WHERE id = :id", nativeQuery = true)
    void deleteCourseOffering(@Param("id") UUID id);
}
