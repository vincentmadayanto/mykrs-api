package com.enigma.my_krs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "m_student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    private UUID id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "student_number", nullable = false, length = 20, unique = true)
    private String studentNumber;

    @Column(name = "email", nullable = false, length = 40, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 15, unique = true)
    private String phoneNumber;

    @Column(name = "academic_year", nullable = false)
    private Integer academicYear;

    @Column(name = "semester", nullable = false)
    private Integer semester;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

}
