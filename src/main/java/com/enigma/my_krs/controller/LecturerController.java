package com.enigma.my_krs.controller;

import com.enigma.my_krs.constant.Constant;
import com.enigma.my_krs.dto.request.LecturerRequest;
import com.enigma.my_krs.dto.response.LecturerResponse;
import com.enigma.my_krs.service.LecturerService;
import com.enigma.my_krs.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.LECTURER_API)
@RequiredArgsConstructor
@Tag(name = "Lecturer", description = "Lecturer Management APIs")
public class LecturerController {
    private final LecturerService lecturerService;

    @Operation(summary = "Create a new lecturer",
            description = "Creates a new lecturer with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Lecturer created successfully",
                    content = @Content(schema = @Schema(implementation = LecturerResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createLecturer(@RequestBody LecturerRequest request) {
        LecturerResponse lecturerResponse = lecturerService.addLecturer(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_LECTURER, lecturerResponse);
    }
}