package com.enigma.my_krs.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private UUID id;
    private String courseCode;
    private String name;
    private Integer credits;
}
