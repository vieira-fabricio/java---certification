package com.certification.rocketseat.modules.student.controllers;

import com.certification.rocketseat.modules.student.dto.StudentCertificationAnswerDto;
import com.certification.rocketseat.modules.student.dto.VerifyHasCertificationDto;
import com.certification.rocketseat.modules.student.entities.CertificationStudentEntity;
import com.certification.rocketseat.modules.student.usecases.StudentCertificationAnswersUseCase;
import com.certification.rocketseat.modules.student.usecases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;
    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verifyifhascertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDto verifyHasCertificationDto) {
        //email
        //tecnologia
        var result = verifyIfHasCertificationUseCase.execute(verifyHasCertificationDto);
        if (result) {
            return "Usuário já fez a prova!";
        }
        return "O aluno pode fazer a prova!";
    }

    @PostMapping("/certification/answer")
    public CertificationStudentEntity certificationAnswer(@RequestBody StudentCertificationAnswerDto studentCertificationAnswerDto) {

        return studentCertificationAnswersUseCase.execute(studentCertificationAnswerDto);
    }
}
