package com.enigma.my_krs.repository;

import com.enigma.my_krs.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO m_lecturer (id, name, email) VALUES (:id, :name, :email)", nativeQuery = true)
    void insertLecturer(@Param("id") UUID id,
                        @Param("name") String name,
                        @Param("email") String email);

    @Query(value = "SELECT * FROM m_lecturer WHERE id = :id", nativeQuery = true)
    Optional<Lecturer> findLecturerById(@Param("id") UUID id);
}
