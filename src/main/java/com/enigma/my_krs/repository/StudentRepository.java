package com.enigma.my_krs.repository;

import com.enigma.my_krs.entity.Student;
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
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO m_student " +
            "(id, name, student_number ,email, phone_number, academic_year, semester) " +
            "VALUES (:id, :name, :student_number, :email, :phone_number, :academic_year, :semester)",
            nativeQuery = true)
    void insertStudent(@Param("id") UUID id,
                       @Param("name") String name,
                       @Param("student_number") String studentNumber,
                       @Param("email") String email,
                       @Param("phone_number") String phoneNumber,
                       @Param("academic_year") Integer academicYear,
                       @Param("semester") Integer semester);

    @Query(value = "SELECT * FROM m_student", nativeQuery = true)
    List<Student> findAllStudents();

    @Query(value = "SELECT * FROM m_student WHERE id = :id", nativeQuery = true)
    Optional<Student> findStudentById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_student SET name = :name, " +
            "student_number = :student_number, " +
            "email = :email, " +
            "phone_number = :phone_number, " +
            "academic_year = :academic_year, " +
            "semester = :semester WHERE id = :id", nativeQuery = true)
    void updateStudent(@Param("id") UUID id,
                       @Param("name") String name,
                       @Param("student_number") String studentNumber,
                       @Param("email") String email,
                       @Param("phone_number") String phoneNumber,
                       @Param("academic_year") Integer academicYear,
                       @Param("semester") Integer semester);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM m_student WHERE id = :id", nativeQuery = true)
    void deleteStudent(@Param("id") UUID id);
}
