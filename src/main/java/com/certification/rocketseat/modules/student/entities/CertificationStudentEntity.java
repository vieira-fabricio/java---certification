package com.certification.rocketseat.modules.student.entities;

import com.certification.rocketseat.modules.questions.entidades.Alternative;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
@Builder
public class CertificationStudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 100)
    private String technology;
    @Column(length = 10)
    private int grade;
    @Column(name = "student_id")
    private UUID studentID;
    @Column(name = "student_name")
    private String StudentName;
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonIgnore
    private StudentEntity studentEntity;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_certification_id", insertable = false, updatable = false)
    @JsonIgnore
    List<AnswersCertificationsEntity> answersCertificationsEntity;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "Question_result_id")
    private List<QuestionResultEntity> questionResults;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public CertificationStudentEntity(UUID id, String technology, String grade, UUID studentID, String studentName, StudentEntity studentEntity, List<AnswersCertificationsEntity> answersCertificationsEntities, List<QuestionResultEntity> questionResults, LocalDateTime createdAt) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public List<AnswersCertificationsEntity> getAnswersCertificationsEntity() {
        return answersCertificationsEntity;
    }

    public void setAnswersCertificationsEntity(List<AnswersCertificationsEntity> answersCertificationsEntity) {
        this.answersCertificationsEntity = answersCertificationsEntity;
    }

    public List<QuestionResultEntity> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResultEntity> questionResults) {
        this.questionResults = questionResults;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static class Builder {
        private UUID id;
        private String technology;
        private String grade;
        private UUID studentID;
        private String studentName;
        private StudentEntity studentEntity;
        private List<AnswersCertificationsEntity> answersCertificationsEntities;
        private List<QuestionResultEntity> questionResults;
        private LocalDateTime createdAt;

        public CertificationStudentEntity.Builder technology(String technology) {
            this.technology = technology;
            return this;
        }

        public CertificationStudentEntity.Builder grade(String grade) {
            this.grade = grade;
            return this;
        }

        public CertificationStudentEntity.Builder studentID(UUID studentID) {
            this.studentID = studentID;
            return this;
        }

        public CertificationStudentEntity.Builder studentName(String studentName) {
            this.studentName = studentName;
            return this;
        }

        public CertificationStudentEntity.Builder questionResults(List<QuestionResultEntity> questionResults) {
            this.questionResults = questionResults;
            return this;
        }

        public CertificationStudentEntity build() {
            return new CertificationStudentEntity(id, technology, grade, studentID, studentName, studentEntity, answersCertificationsEntities, questionResults, createdAt);
        }

        public CertificationStudentEntity.Builder alternativeEntity(List<Alternative> alternativeEntities) {
            return this;
        }
    }
}
