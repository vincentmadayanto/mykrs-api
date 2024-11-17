package com.enigma.my_krs.service.impl;

import com.enigma.my_krs.dto.request.StudentRequest;
import com.enigma.my_krs.dto.response.StudentResponse;
import com.enigma.my_krs.entity.Student;
import com.enigma.my_krs.repository.StudentRepository;
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
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public StudentResponse addStudent(StudentRequest request) {
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .studentNumber(request.getStudentNumber())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .academicYear(request.getAcademicYear())
                .semester(request.getSemester())
                .build();
        studentRepository.insertStudent(student.getId(), student.getName(), student.getStudentNumber(), student.getEmail(), student.getPhoneNumber(), student.getAcademicYear(), student.getSemester());

        return mapToStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAllStudents().stream().map(this::mapToStudentResponse).collect(Collectors.toList());
    }

    public Student getOne(UUID id) {
        return studentRepository.findStudentById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @Override
    public StudentResponse getStudentById(UUID id) {
        return mapToStudentResponse(getOne(id));
    }

    @Override
    public StudentResponse updateStudentById(UUID id, StudentRequest request) {
        Student student = getOne(id);
        student.setName(request.getName());
        student.setStudentNumber(request.getStudentNumber());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setSemester(request.getSemester());
        student.setAcademicYear(request.getAcademicYear());
        studentRepository.updateStudent(student.getId(), student.getName(), student.getStudentNumber(), student.getEmail(), student.getPhoneNumber(), student.getAcademicYear(), student.getSemester());
        return mapToStudentResponse(student);
    }

    @Override
    public void deleteStudentById(UUID id) {
        studentRepository.deleteStudent(getOne(id).getId());
    }

    private StudentResponse mapToStudentResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .studentNumber(student.getStudentNumber())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .academicYear(student.getAcademicYear())
                .semester(student.getSemester())
                .build();
    }
}
