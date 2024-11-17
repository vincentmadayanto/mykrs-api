package com.enigma.my_krs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "t_course_offering")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseOffering {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "class", nullable = false)
    private Character classRoom;

    @Column(name = "available_seats", nullable = false, columnDefinition = "bigint check (available_seats > 0)")
    private Integer availableSeats;

    @Column(name = "schedule", nullable = false, length = 25)
    private String schedule;

    @ManyToOne
    @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;
}
