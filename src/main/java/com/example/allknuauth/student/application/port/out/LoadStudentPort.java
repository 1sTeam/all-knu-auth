package com.example.allknuauth.student.application.port.out;

import com.example.allknuauth.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public interface LoadStudentPort {
    Student loadStudentByStudentId(String studentId);
}
