package com.enigma.my_krs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "m_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    private UUID id;

    @Column(name = "course_code", nullable = false, length = 15, unique = true)
    private String courseCode;

    @Column(name = "name", nullable = false, length = 40, unique = true)
    private String name;

    @Column(name = "credits", nullable = false)
    private Integer credits;
}
