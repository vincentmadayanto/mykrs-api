package com.enigma.my_krs.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponse {
    private UUID id;
    private UUID studentId;
    private Integer totalQuota;
    private Integer remainingQuota;
}
