package com.certification.rocketseat.modules.student.controllers;

import com.certification.rocketseat.modules.student.dto.VerifyHasCertificationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @PostMapping("/verifyifhascertification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDto verifyHasCertificationDto) {
        //email
        //tecnologia

        System.out.println(verifyHasCertificationDto);
        return "O aluno pode fazer a prova!";
    }
}
