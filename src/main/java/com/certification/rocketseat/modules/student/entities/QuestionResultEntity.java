package com.certification.rocketseat.modules.student.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "question_result")
public class QuestionResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "certification_id")
    @JsonIgnore
    private CertificationStudentEntity certificationStudentEntity;

    private UUID questionId;

    private boolean isCorrect;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CertificationStudentEntity getCertificationStudentEntity() {
        return certificationStudentEntity;
    }

    public void setCertificationStudentEntity(CertificationStudentEntity certificationStudentEntity) {
        this.certificationStudentEntity = certificationStudentEntity;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
