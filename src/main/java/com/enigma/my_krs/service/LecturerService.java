package com.enigma.my_krs.service;

import com.enigma.my_krs.dto.request.LecturerRequest;
import com.enigma.my_krs.dto.response.LecturerResponse;
import com.enigma.my_krs.entity.Lecturer;

import java.util.UUID;

public interface LecturerService {
    LecturerResponse addLecturer(LecturerRequest request);
    Lecturer getOne(UUID id);
}
