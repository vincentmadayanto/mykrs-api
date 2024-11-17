package com.enigma.my_krs.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LecturerResponse {
    private UUID id;
    private String name;
    private String email;
}
