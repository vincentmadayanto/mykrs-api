package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
import com.enigma.my_krs.dto.request.StudentRequest;
import com.enigma.my_krs.dto.response.StudentResponse;
import com.enigma.my_krs.service.StudentService;
import com.enigma.my_krs.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.STUDENT_API)
@RequiredArgsConstructor
@Tag(name = "Student", description = "Student Management APIs")
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "Create a new student",
            description = "Creates a new student with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Student created successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentRequest request) {
        StudentResponse savedStudent = studentService.addStudent(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATED_STUDENT, savedStudent);
    }

    @Operation(summary = "Get all students",
            description = "Retrieves a list of all registered students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved all students",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<StudentResponse> studentResponses = studentService.getAllStudents();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_STUDENT, studentResponses);
    }

    @Operation(summary = "Get student by ID",
            description = "Retrieves a specific student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Successfully retrieved the student",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "Student not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @GetMapping(path = "/{studentId}")
    public ResponseEntity<?> getStudentById(
            @Parameter(description = "ID of the student to retrieve")
            @PathVariable("studentId") UUID id) {
        StudentResponse studentResponse = studentService.getStudentById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_STUDENT_BY_ID, studentResponse);
    }

    @Operation(summary = "Update student by ID",
            description = "Updates an existing student's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student updated successfully",
                    content = @Content(schema = @Schema(implementation = StudentResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Student not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PutMapping(path = "/{studentId}")
    public ResponseEntity<?> updateStudentById(
            @Parameter(description = "ID of the student to update")
            @PathVariable("studentId") UUID id,
            @RequestBody StudentRequest request) {
        StudentResponse studentResponse = studentService.updateStudentById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_STUDENT_BY_ID, studentResponse);
    }

    @Operation(summary = "Delete student by ID",
            description = "Deletes a specific student from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404",
                    description = "Student not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @DeleteMapping(path = "/{studentId}")
    public ResponseEntity<?> deleteStudentById(
            @Parameter(description = "ID of the student to delete")
            @PathVariable("studentId") UUID id) {
        studentService.deleteStudentById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_STUDENT_BY_ID, null);
    }
}