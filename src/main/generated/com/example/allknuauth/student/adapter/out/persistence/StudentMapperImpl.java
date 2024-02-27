package com.example.allknuauth.student.adapter.out.persistence;

import com.example.allknuauth.student.domain.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T22:40:05+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toDomain(StudentEntity student) {
        if ( student == null ) {
            return null;
        }

        Student.StudentBuilder student1 = Student.builder();

        student1.id( student.getId() );
        student1.studentId( student.getStudentId() );
        student1.major( student.getMajor() );
        student1.name( student.getName() );

        return student1.build();
    }

    @Override
    public StudentEntity toEntity(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentEntity.StudentEntityBuilder studentEntity = StudentEntity.builder();

        studentEntity.id( student.getId() );
        studentEntity.studentId( student.getStudentId() );
        studentEntity.name( student.getName() );
        studentEntity.major( student.getMajor() );

        return studentEntity.build();
    }
}
