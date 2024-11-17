package com.enigma.my_krs.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseOfferingRequest {
    private UUID course;
    private Character classRoom;
    private Integer availableSeats;
    private String schedule;
    private UUID lecturer;
}
