package com.enigma.my_krs.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseOfferingResponse {
    private UUID id;
    private UUID course;
    private Character classRoom;
    private Integer availableSeats;
    private String schedule;
    private UUID lecturer;
}
