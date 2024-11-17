package com.enigma.my_krs.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentDetailRequest {
    private UUID enrollmentId;
    private UUID courseOfferingId;
}

