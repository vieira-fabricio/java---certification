package com.certification.rocketseat.modules.student.usecases;

import com.certification.rocketseat.modules.student.dto.VerifyHasCertificationDto;
import com.certification.rocketseat.modules.student.repository.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {
    @Autowired
    private CertificationStudentRepository repository;

    public boolean execute(VerifyHasCertificationDto dto) {
        var result = this.repository.findStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
