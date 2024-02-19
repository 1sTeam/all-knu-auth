package com.example.allknuauth.student.adapter.out.persistence;
import com.example.allknuauth.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    Student toDomain(StudentEntity student);
}
