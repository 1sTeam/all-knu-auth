package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.consent.application.port.out.LoadConsentPort;
import com.example.allknuauth.consent.application.port.out.UpdateConsentPort;
import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ConsentPersistenceAdapter implements UpdateConsentPort, LoadConsentPort {
    private final ConsentRepository consentRepository;
    private final ConsentMapper consentMapper;

    @Override
    public Consent loadConsentByStudent(Student student) {
        ConsentEntity consent = consentRepository.findByStudent(student);
        if (consent == null){
            return null;
        }
        return consentMapper.mapToConsentEntity(consent);
    }

    @Override
    public Consent updateConsent(Consent consent) {
        ConsentEntity consentEntity = ConsentEntity.builder()
                .id(consent.getId())
                .isStudentId(consent.isStudentId())
                .isName(consent.isName())
                .isMajor(consent.isMajor())
                .build();
        consentEntity = consentRepository.save(consentEntity);
        return consentMapper.mapToConsentEntity(consentEntity);
    }
}
