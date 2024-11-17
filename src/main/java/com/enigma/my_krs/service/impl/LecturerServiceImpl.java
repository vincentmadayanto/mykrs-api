package com.enigma.my_krs.service.impl;

import com.enigma.my_krs.dto.request.LecturerRequest;
import com.enigma.my_krs.dto.response.LecturerResponse;
import com.enigma.my_krs.entity.Lecturer;
import com.enigma.my_krs.repository.LecturerRepository;
import com.enigma.my_krs.service.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService {
    private final LecturerRepository lecturerRepository;

    @Override
    public LecturerResponse addLecturer(LecturerRequest request) {
        Lecturer lecturer = Lecturer.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .email(request.getEmail())
                .build();
        lecturerRepository.insertLecturer(lecturer.getId(), lecturer.getName(), lecturer.getEmail());
        return mapToLecturerResponse(lecturer);
    }

    @Override
    public Lecturer getOne(UUID id) {
        return lecturerRepository.findLecturerById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecturer not found"));
    }

    private LecturerResponse mapToLecturerResponse(Lecturer lecturer) {
        return LecturerResponse.builder()
                .id(lecturer.getId())
                .name(lecturer.getName())
                .email(lecturer.getEmail())
                .build();
    }
}
