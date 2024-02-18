package com.example.allknuauth.student.adapter.out.persistence;

import com.example.allknuauth.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
class StudentMapper {
    Student mapToStudentEntity(StudentEntity student) {
        return Student.withId(student.getId(), student.getStudentId(), student.getMajor(), student.getName());
    }
}
