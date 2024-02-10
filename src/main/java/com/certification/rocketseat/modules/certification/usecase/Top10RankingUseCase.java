package com.certification.rocketseat.modules.certification.usecase;

import com.certification.rocketseat.modules.student.entities.CertificationStudentEntity;
import com.certification.rocketseat.modules.student.repository.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingUseCase {

    @Autowired
    private CertificationStudentRepository repository;

    public List<CertificationStudentEntity> execute() {
        return repository.findTop10ByOrderByGradeDesc();
    }
}
