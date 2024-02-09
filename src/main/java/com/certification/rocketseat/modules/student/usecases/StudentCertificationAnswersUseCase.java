package com.certification.rocketseat.modules.student.usecases;

import com.certification.rocketseat.modules.questions.entidades.AlternativeEntity;
import com.certification.rocketseat.modules.questions.entidades.QuestionEntity;
import com.certification.rocketseat.modules.questions.repository.QuestionRepository;
import com.certification.rocketseat.modules.student.dto.StudentCertificationAnswerDto;
import com.certification.rocketseat.modules.student.entities.AnswersCertificationsEntity;
import com.certification.rocketseat.modules.student.entities.CertificationStudentEntity;
import com.certification.rocketseat.modules.student.entities.StudentEntity;
import com.certification.rocketseat.modules.student.repository.CertificationStudentRepository;
import com.certification.rocketseat.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private CertificationStudentRepository certificationRepository;

    public CertificationStudentEntity execute(StudentCertificationAnswerDto dto) {
        //buscar as alternativas das perguntas
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        dto.getQuestionAnswerDto().forEach(questionAnswer -> {
            var questionResultOptional = questionsEntity.stream().filter(question -> question.getId()
                    .equals(questionAnswer.getQuestionId())).findFirst();

            if (questionResultOptional.isPresent()) {
                var questionResult = questionResultOptional.get();

                var correctAlternativeOptional = questionResult.getAlternativeEntity().stream()
                        .filter(AlternativeEntity::isCorrect).findFirst();

                if (correctAlternativeOptional.isPresent()) {
                    var correctAlternative = correctAlternativeOptional.get();

                    questionAnswer.setCorrect(correctAlternative.getId().equals(questionAnswer.getAlternativeId()));
                    var answerCertificationsEntity = AnswersCertificationsEntity.builder()
                            .answerID(questionAnswer.getAlternativeId())
                            .questionID(questionAnswer.getQuestionId())
                            .isCorrect(questionAnswer.isCorrect()).build();

                    answersCertifications.add(answerCertificationsEntity);
                }
            }
        });

        //verificar se o estudante existe pelo email
        UUID studendID = repository.findByEmail(dto.getEmail())
                .map(StudentEntity::getId)
                .orElseGet(() -> {
                    StudentEntity studentCreated = StudentEntity.builder()
                            .email(dto.getEmail())
                            .build();
                    return repository.save(studentCreated).getId();
                });

        /*var studentExist = repository.findByEmail(dto.getEmail());
        UUID studendID;
        if (studentExist.isEmpty()){
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = repository.save(studentCreated);
            studendID = studentCreated.getId();
        } else {
            studendID = studentExist.get().getId();
        }*/

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
                .technology(dto.getTechnology())
                .studentID(studendID)
                .build();

        var certificationStudentCreated = certificationRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntity(answersCertifications);

        certificationRepository.save(certificationStudentEntity);

        return certificationStudentCreated;
        //salvar as informações da certificação
    }
}
