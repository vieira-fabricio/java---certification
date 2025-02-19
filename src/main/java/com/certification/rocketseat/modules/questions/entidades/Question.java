package com.certification.rocketseat.modules.questions.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Builder
@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 50)
    private String technology;
    private String description;

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Alternative> alternatives;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
