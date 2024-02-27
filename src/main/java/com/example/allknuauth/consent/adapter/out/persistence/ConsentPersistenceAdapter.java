package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.consent.application.port.out.LoadConsentPort;
import com.example.allknuauth.consent.application.port.out.UpdateConsentPort;
import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.student.adapter.out.persistence.StudentMapper;
import com.example.allknuauth.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class ConsentPersistenceAdapter implements UpdateConsentPort, LoadConsentPort {
    private final ConsentRepository consentRepository;
    private final ConsentMapper consentMapper;
    private final StudentMapper studentMapper;

    @Override
    public Consent loadConsentByStudent(Student student) {
        ConsentEntity consent = consentRepository.findByStudent(student);
        if (consent == null) {
            return null;
        }
        return consentMapper.toDomain(consent);
    }

    @Override
    public Consent loadConsentByStudentAndType(Student student, ConsentType type) {
        ConsentEntity consent = consentRepository.findByStudentAndType(student, type);
        if (consent == null) {
            return null;
        }
        return consentMapper.toDomain(consent);
    }

    @Override
    public Consent updateConsent(Consent consent) {
        ConsentEntity consentEntity = ConsentEntity.builder()
                .id(consent.getId())
                .type(consent.getType())
                .value(consent.isValue())
                .student(studentMapper.toEntity(consent.getStudent()))
                .build();
        consentEntity = consentRepository.save(consentEntity);
        return consentMapper.toDomain(consentEntity);
    }
}
