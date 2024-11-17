package com.enigma.my_krs.controller;

import com.enigma.my_krs.dto.request.LecturerRequest;
import com.enigma.my_krs.dto.response.LecturerResponse;
import com.enigma.my_krs.service.LecturerService;
import com.enigma.my_krs.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lecturers")
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    @PostMapping
    public ResponseEntity<?> createLecturer(@RequestBody LecturerRequest request) {
        LecturerResponse lecturerResponse = lecturerService.addLecturer(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, "Lecturer Created Successfully", lecturerResponse);
    }
}
