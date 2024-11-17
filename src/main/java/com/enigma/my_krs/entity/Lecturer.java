package com.enigma.my_krs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "m_lecturer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lecturer {
    @Id
    private UUID id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;
}
