package com.enigma.my_krs.controller;

import com.enigma.my_krs.dto.request.StudentRequest;
import com.enigma.my_krs.dto.response.StudentResponse;
import com.enigma.my_krs.service.StudentService;
import com.enigma.my_krs.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest request) {
        StudentResponse savedStudent = studentService.addStudent(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Student created successfully", savedStudent);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<StudentResponse> studentResponses = studentService.getAllStudents();
        return ResponseUtil.buildResponse(HttpStatus.OK, "Get All Students successfully", studentResponses);
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable("studentId") UUID id) {
        StudentResponse studentResponse = studentService.getStudentById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Get Student by id successfully", studentResponse);
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<?> updateStudentById(@PathVariable("studentId") UUID id, @RequestBody StudentRequest request) {
        StudentResponse studentResponse = studentService.updateStudentById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Student updated successfully", studentResponse);
    }

    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("studentId") UUID id) {
        studentService.deleteStudentById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, "Student Deleted successfully", null);
    }
}
