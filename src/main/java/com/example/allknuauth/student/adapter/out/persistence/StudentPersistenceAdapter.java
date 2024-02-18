package com.example.allknuauth.student.adapter.out.persistence;

import com.example.allknuauth.student.application.port.out.LoadStudentPort;
import com.example.allknuauth.student.application.port.out.UpdateStudentPort;
import com.example.allknuauth.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements LoadStudentPort, UpdateStudentPort {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public Student loadStudentByStudentId(String studentId) {
        StudentEntity student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            return null;
        }
        return studentMapper.mapToStudentEntity(student);
    }

    @Override
    public Student updateStudent(Student student) {
        StudentEntity studentEntity = StudentEntity.builder()
                .id(student.getId())
                .studentId(student.getStudentId())
                .major(student.getMajor())
                .name(student.getName())
                .build();
        studentEntity = studentRepository.save(studentEntity);
        return studentMapper.mapToStudentEntity(studentEntity);
    }
}
