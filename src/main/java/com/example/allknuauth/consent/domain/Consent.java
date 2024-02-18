package com.example.allknuauth.consent.domain;

import com.example.allknuauth.student.domain.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Consent {
    private Long id;
    private boolean studentId;
    private boolean name;
    private boolean major;
    private Student student;


    public static Consent withId(Long id, boolean studentId, boolean name, boolean major, Student student) {
        return new Consent(id, studentId, name, major, student);
    }

    public static Consent withoutId(boolean studentId, boolean name, boolean major, Student student) {
        return new Consent(null, studentId, name, major, student);
    }
}
