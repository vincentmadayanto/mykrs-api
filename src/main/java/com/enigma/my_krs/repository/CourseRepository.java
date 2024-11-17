package com.enigma.my_krs.repository;

import com.enigma.my_krs.entity.Course;
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
public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO m_course (id, course_code, name, credits) VALUES (:id, :course_code, :name, :credits)", nativeQuery = true)
    void insertCourse(@Param("id") UUID id,
                      @Param("course_code") String courseCode,
                      @Param("name") String name,
                      @Param("credits") Integer credits);

    @Query(value = "SELECT * FROM m_course", nativeQuery = true)
    List<Course> findAllCourses();

    @Query(value = "SELECT * FROM m_course WHERE id = :id", nativeQuery = true)
    Optional<Course> findCourseById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_course SET course_code = :course_code, name = :name, credits = :credits WHERE id = :id", nativeQuery = true)
    void updateCourse(@Param("id") UUID id,
                      @Param("course_code") String courseCode,
                      @Param("name") String name,
                      @Param("credits") Integer credits);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM m_course WHERE id = :id", nativeQuery = true)
    void deleteCourse(@Param("id") UUID id);
}
