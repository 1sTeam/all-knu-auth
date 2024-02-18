package com.example.allknuauth.student.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
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
}
