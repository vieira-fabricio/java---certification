package com.certification.rocketseat.modules.student.repository;

import com.certification.rocketseat.modules.student.entities.CertificationStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity,UUID> {
    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.tecnology = :tecnology")
    List<CertificationStudentEntity> findStudentEmailAndTecnology(String email, String tecnology);
}
