package com.certification.rocketseat.modules.student.usecases;

import com.certification.rocketseat.modules.questions.entidades.Alternative;
import com.certification.rocketseat.modules.questions.entidades.Question;
import com.certification.rocketseat.modules.questions.repository.QuestionRepository;
import com.certification.rocketseat.modules.student.dto.QuestionAnswerDto;
import com.certification.rocketseat.modules.student.dto.StudentCertificationAnswerDto;
import com.certification.rocketseat.modules.student.dto.VerifyHasCertificationDto;
import com.certification.rocketseat.modules.student.entities.AnswersCertificationsEntity;
import com.certification.rocketseat.modules.student.entities.CertificationStudentEntity;
import com.certification.rocketseat.modules.student.entities.QuestionResultEntity;
import com.certification.rocketseat.modules.student.entities.StudentEntity;
import com.certification.rocketseat.modules.student.repository.CertificationStudentRepository;
import com.certification.rocketseat.modules.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersUseCase {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private StudentRepository repository;
    @Autowired
    private CertificationStudentRepository certificationRepository;
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDto dto) throws Exception {

        verifyIfExistingCertification(dto.getEmail(), dto.getTechnology(), dto.getName());

        List<AnswersCertificationsEntity> answersCertifications = processQuestionAnswers(dto);

        return saveCertification(dto, answersCertifications);
    }

    //Método que verifica se o candidato já tirou uma certificação para a mesma tecnologia.
    private void verifyIfExistingCertification(String email, String technology, String name) throws Exception {
        boolean hasCertification = verifyIfHasCertificationUseCase.execute(
                new VerifyHasCertificationDto(email, technology, name));

        if (hasCertification) {
            throw new Exception("Você já tirou a sua certificação!");
        }
    }

    //Método que busca as alternativas das perguntas.
    private List<AnswersCertificationsEntity> processQuestionAnswers(StudentCertificationAnswerDto dto) {
        List<Question> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionAnswerDto().forEach(questionAnswer -> {
            Optional<Question> questionResultOptional = questionsEntity.stream()
                    .filter(question -> question.getId().equals(questionAnswer.getQuestionId()))
                            .findFirst();

            questionResultOptional.ifPresent(questionResult -> {
                Optional<Alternative> correctAlternativeOptional = questionResult.getAlternatives().stream()
                        .filter(Alternative::isCorrect)
                        .findFirst();

                correctAlternativeOptional.ifPresent(correctAlternative -> {
                    boolean isCorrect = correctAlternative.getId().equals(questionAnswer.getAlternativeId());
                    questionAnswer.setCorrect(isCorrect);
                    correctAnswers.incrementAndGet();

                    AnswersCertificationsEntity answersCertificationsEntity = new AnswersCertificationsEntity.Builder()
                            .answerID(questionAnswer.getAlternativeId())
                            .questionID(questionAnswer.getQuestionId())
                            .isCorrect(isCorrect)
                            .build();

                    answersCertifications.add(answersCertificationsEntity);
                });
            });
        });
        return answersCertifications;
    }

    //Método que vai salvar os dados da certificação.
    private CertificationStudentEntity saveCertification(StudentCertificationAnswerDto dto,
                                                         List<AnswersCertificationsEntity>
                                                                 answersCertifications) {
        List<QuestionResultEntity> questionResults = dto.getQuestionAnswerDto().stream()
                .map(questionAnswer -> {
                    QuestionResultEntity questionResult = new QuestionResultEntity();
                    questionResult.setQuestionId(questionAnswer.getQuestionId());
                    questionResult.setCorrect(questionAnswer.isCorrect());
                    return questionResult;
                })
                .toList();

        //verifica se o estudante existe pelo email
        UUID studendID = getOrCreateStudentId(dto.getEmail());

        // Calcula o número total de respostas corretas
        long correctAnswersCount = dto.getQuestionAnswerDto().stream()
                .filter(QuestionAnswerDto::isCorrect)
                .count();

        // Calcula a nota com base no número total de respostas corretas
        double grade = (double) correctAnswersCount / dto.getQuestionAnswerDto().size() * 3;

        CertificationStudentEntity certificationStudentEntity = new CertificationStudentEntity.Builder()
                .technology(dto.getTechnology())
                .studentID(studendID)
                .studentName(dto.getName())
                .grade(String.valueOf(grade))
                .questionResults(questionResults)
                .build();

        CertificationStudentEntity certificationStudentCreated = certificationRepository
                .save(certificationStudentEntity);

        answersCertifications.stream().forEach(answersCertification -> {
            answersCertification.setCertificationID(certificationStudentEntity.getId());
            answersCertification.setCertificationStudentEntity(certificationStudentEntity);
        });

        certificationStudentEntity.setAnswersCertificationsEntity(answersCertifications);

        return certificationStudentCreated;
    }

    //Método responsável por verificar se o estudante já existe pelo email.
    private UUID getOrCreateStudentId(String email) {
        Optional<StudentEntity> studentOptional = repository.findByEmail(email);
        return studentOptional.map(StudentEntity::getId)
                .orElseGet(() -> {
                    StudentEntity newStudent = new StudentEntity.Builder().email(email).build();
                    return repository.save(newStudent).getId();
                });
    }
}
