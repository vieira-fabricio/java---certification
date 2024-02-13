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
}
