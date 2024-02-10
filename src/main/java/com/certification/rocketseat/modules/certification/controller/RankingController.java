package com.certification.rocketseat.modules.certification.controller;

import com.certification.rocketseat.modules.certification.usecase.Top10RankingUseCase;
import com.certification.rocketseat.modules.student.entities.CertificationStudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private Top10RankingUseCase top10RankingUseCase;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return top10RankingUseCase.execute();
    }
}
