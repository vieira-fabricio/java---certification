package com.certification.rocketseat.modules.student.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudentEntity {

    private UUID id;
    private UUID studentID;
    private String tecnology;
    private int grade;
    List<AnswersCertificationsEntity> answersCertificationsEntity;

}
