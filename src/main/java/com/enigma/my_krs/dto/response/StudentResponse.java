package com.enigma.my_krs.dto.response;

import com.enigma.my_krs.entity.Enrollment;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private UUID id;
    private String name;
    private String studentNumber;
    private String email;
    private String phoneNumber;
    private Integer academicYear;
    private Integer semester;
//    private List<Enrollment> enrollments;
}
