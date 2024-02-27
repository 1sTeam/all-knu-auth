package com.example.allknuauth.consent.application.service;

import com.example.allknuauth.consent.application.port.in.LoadConsentCommand;
import com.example.allknuauth.consent.application.port.in.LoadConsentUseCase;
import com.example.allknuauth.consent.application.port.out.LoadConsentPort;
import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.global.exception.errors.NotFoundStudentException;
import com.example.allknuauth.student.application.port.out.LoadStudentPort;
import com.example.allknuauth.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class LoadConsentService implements LoadConsentUseCase {
    private final LoadConsentPort loadConsentPort;
    private final LoadStudentPort loadStudentPort;

    @Override
    @Transactional
    public LoadConsentCommand loadConsents(String studentId) {
        Student student = loadStudentPort.loadStudentByStudentId(studentId);
        if (student == null){
           throw new NotFoundStudentException();
        }
        Map<ConsentType, Boolean> consents = new HashMap<>();

        for (ConsentType type : ConsentType.values()) {
            Consent consent = loadConsentPort.loadConsentByStudentAndType(student, type);
            if (consent == null) {
                consents.put(type, false);
            } else {
                consents.put(type, consent.isConsent());
            }
        }
        return new LoadConsentCommand(consents);
    }
}현
