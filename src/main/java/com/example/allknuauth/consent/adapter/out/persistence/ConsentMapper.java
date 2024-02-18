package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.student.adapter.out.persistence.StudentEntity;
import com.example.allknuauth.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
class ConsentMapper {
    Consent mapToConsentEntity(ConsentEntity consent){
        return Consent.withId(consent.getId(), consent.isStudentId(), consent.isName(), consent.isMajor(), mapToStudentEntity(consent.getStudent()));
    }
    Student mapToStudentEntity(StudentEntity student) {
        return Student.withId(student.getId(), student.getStudentId(), student.getMajor(), student.getName());
    }
}
