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
    private boolean value;
    private Student student;


    public static Consent withId(Long id, ConsentType type, boolean value, Student student) {
        return new Consent(id, type, value, student);
    }

    public static Consent withoutId(ConsentType type, boolean value, Student student) {
        return new Consent(null, type, value, student);
    }
}
