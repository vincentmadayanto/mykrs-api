package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.request.StudentRequest;
import com.enigma.my_krs.dto.response.StudentResponse;
import com.enigma.my_krs.entity.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    StudentResponse addStudent(StudentRequest request);
    List<StudentResponse> getAllStudents();
    Student getOne(UUID id);
    StudentResponse getStudentById(UUID id);
    StudentResponse updateStudentById(UUID id, StudentRequest request);
    void deleteStudentById(UUID id);
}
