package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.request.LecturerRequest;
import com.enigma.my_krs.dto.response.LecturerResponse;
import com.enigma.my_krs.entity.Lecturer;

import java.util.List;
import java.util.UUID;

public interface LecturerService {
    LecturerResponse addLecturer(LecturerRequest request);
    Lecturer getOne(UUID id);
    LecturerResponse getLecturerById(UUID id);
    List<LecturerResponse> getAllLecturer();
    LecturerResponse updateLecturerById(UUID id, LecturerRequest request);
    void deleteLecturerById(UUID id);
}
