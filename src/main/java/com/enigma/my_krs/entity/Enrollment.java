package com.enigma.my_krs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_enrollment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enrollment {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name= "total_quota", nullable = false)
    private Integer totalQuota;

    @Column(name= "remaining_quota", nullable = false)
    private Integer remainingQuota;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<EnrollmentDetail> enrollmentDetails;
}
