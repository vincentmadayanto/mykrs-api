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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constant.LECTURER_API)
@RequiredArgsConstructor
@Tag(name = "Lecturer", description = "Lecturer Management APIs")
public class LecturerController {
    private final LecturerService lecturerService;

    @Operation(summary = "Create a new lecturer", description = "Creates a new lecturer with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Lecturer created successfully",
                    content = @Content(schema = @Schema(implementation = LecturerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<?> createLecturer(@RequestBody LecturerRequest request) {
        LecturerResponse lecturerResponse = lecturerService.addLecturer(request);
        return ResponseUtil.buildResponse(HttpStatus.CREATED, Constant.SUCCESS_CREATE_LECTURER, lecturerResponse);
    }

    @Operation(summary = "Get all lecturers", description = "Fetches all lecturers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all lecturers successfully",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    ResponseEntity<?> getAllLecturers() {
        List allLecturer = lecturerService.getAllLecturer();
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_ALL_LECTURER, allLecturer);
    }

    @Operation(summary = "Get lecturer by ID", description = "Fetches a lecturer using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched lecturer successfully",
                    content = @Content(schema = @Schema(implementation = LecturerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Lecturer not found",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping(path = "/{lecturerId}")
    ResponseEntity<?> getLecturerById(@PathVariable("lecturerId") UUID id) {
        LecturerResponse lecturerById = lecturerService.getLecturerById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_FETCH_LECTURER_BY_ID, lecturerById);
    }

    @Operation(summary = "Update lecturer by ID", description = "Updates a lecturer's details using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lecturer updated successfully",
                    content = @Content(schema = @Schema(implementation = LecturerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Lecturer not found",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping(path = "/{lecturerId}")
    ResponseEntity<?> updateLecturerById(@PathVariable("lecturerId") UUID id, @RequestBody LecturerRequest request) {
        LecturerResponse lecturerResponse = lecturerService.updateLecturerById(id, request);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_UPDATE_LECTURER_BY_ID, lecturerResponse);
    }

    @Operation(summary = "Delete lecturer by ID", description = "Deletes a lecturer using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lecturer deleted successfully",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Lecturer not found",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping(path = "/{lecturerId}")
    ResponseEntity<?> deleteLecturerById(@PathVariable("lecturerId") UUID id) {
        lecturerService.deleteLecturerById(id);
        return ResponseUtil.buildResponse(HttpStatus.OK, Constant.SUCCESS_DELETE_LECTURER_BY_ID, null);
    }
}
