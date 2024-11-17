package com.enigma.my_krs.dto.request;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private String courseCode;
    private String name;
    private Integer credits;
}
