package com.example.allknuauth.consent.application.port.out;

import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public interface LoadConsentPort {

    Consent loadConsentByStudent(Student student);

    Consent loadConsentByStudentAndType(Student student, ConsentType type);
}
