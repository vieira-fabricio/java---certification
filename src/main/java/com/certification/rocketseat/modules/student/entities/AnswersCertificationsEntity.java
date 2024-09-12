package com.certification.rocketseat.modules.student.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certification_students")
@Builder
public class AnswersCertificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "certification_id")
    private UUID certificationID;
    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    @JsonIgnore
    private CertificationStudentEntity certificationStudentEntity;

    @Column(name = "student_id")
    private UUID studentID;
    @Column(name = "student_name")
    private String studentName;
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonIgnore
    private StudentEntity studentEntity;
    @Column(name = "question_id")
    private UUID questionID;
    @Column(name = "answer_id")
    private UUID answerID;
    @Column(name = "is_correct")
    private boolean isCorrect;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public AnswersCertificationsEntity(UUID questionID, UUID answerID, boolean isCorrect) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCertificationID() {
        return certificationID;
    }

    public void setCertificationID(UUID certificationID) {
        this.certificationID = certificationID;
    }

    public CertificationStudentEntity getCertificationStudentEntity() {
        return certificationStudentEntity;
    }

    public void setCertificationStudentEntity(CertificationStudentEntity certificationStudentEntity) {
        this.certificationStudentEntity = certificationStudentEntity;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public UUID getQuestionID() {
        return questionID;
    }

    public void setQuestionID(UUID questionID) {
        this.questionID = questionID;
    }

    public UUID getAnswerID() {
        return answerID;
    }

    public void setAnswerID(UUID answerID) {
        this.answerID = answerID;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class Builder {
        private UUID questionID;
        private UUID answerID;
        public boolean isCorrect;

        public AnswersCertificationsEntity.Builder questionID(UUID questionID) {
            this.questionID = questionID;
            return this;
        }

        public AnswersCertificationsEntity.Builder answerID(UUID answerID) {
            this.answerID = answerID;
            return this;
        }

        public AnswersCertificationsEntity.Builder isCorrect(boolean isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }

        public AnswersCertificationsEntity build() {
            return new AnswersCertificationsEntity(questionID, answerID, isCorrect);
        }
    }
}
