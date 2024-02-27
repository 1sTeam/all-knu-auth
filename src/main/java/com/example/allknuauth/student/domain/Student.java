package com.example.allknuauth.student.domain;

import com.example.allknuauth.consent.domain.ConsentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.example.allknuauth.consent.domain.ConsentType.*;


@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Student {
    private Long id;
    private String studentId;
    private String major;
    private String name;


    public static Student withId(Long id, String studentId, String major, String name) {
        return new Student(id, studentId, major, name);
    }

    public static Student withoutId(String studentId, String major, String name) {
        return new Student(null, studentId, major, name);
    }

    public void updateInfo(ConsentType consentType, String info) {
        if (STUDENT_ID == consentType) {
            this.studentId = info;
        }
        if (NAME == consentType) {
            this.name = info;
        }
        if (MAJOR == consentType) {
            this.major = info;
        }
    }
}
