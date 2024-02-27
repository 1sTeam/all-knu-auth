package com.example.allknuauth.consent.domain;

import com.example.allknuauth.student.domain.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Consent {
    private Long id;
    private ConsentType type;
    private boolean consent;
    private Student student;


    public static Consent withId(Long id, ConsentType type, boolean consent, Student student) {
        return new Consent(id, type, consent, student);
    }

    public static Consent withoutId(ConsentType type, boolean consent, Student student) {
        return new Consent(null, type, consent, student);
    }
}
