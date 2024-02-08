package com.certification.rocketseat.modules.questions.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 50)
    private String tecnology;
    private String description;
    @OneToMany
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private List<AlternativeEntity> alternativeEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
