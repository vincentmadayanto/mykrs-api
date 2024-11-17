package com.enigma.my_krs.dto.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequest {
    private UUID studentId;
    private Integer totalQuota;
    private Integer remainingQuota;
}
