package com.enigma.my_krs.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String name;
    private String studentNumber;
    private String email;
    private String phoneNumber;
    private Integer academicYear;
    private Integer semester;
}
