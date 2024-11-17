package com.enigma.my_krs.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LecturerRequest {
    private String name;
    private String email;
}
