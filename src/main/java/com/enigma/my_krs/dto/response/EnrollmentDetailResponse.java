package com.enigma.my_krs.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentDetailResponse {
    private UUID id;
    private UUID enrollmentId;
    private UUID courseOfferingId;
}
