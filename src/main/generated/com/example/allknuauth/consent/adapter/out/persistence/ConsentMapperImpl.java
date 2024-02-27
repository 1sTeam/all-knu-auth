package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.student.adapter.out.persistence.StudentEntity;
import com.example.allknuauth.student.domain.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-27T23:41:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ConsentMapperImpl implements ConsentMapper {

    @Override
    public Consent toDomain(ConsentEntity consent) {
        if ( consent == null ) {
            return null;
        }

        Consent.ConsentBuilder consent1 = Consent.builder();

        consent1.id( consent.getId() );
        consent1.type( consent.getType() );
        consent1.consent( consent.isConsent() );
        consent1.student( studentEntityToStudent( consent.getStudent() ) );

        return consent1.build();
    }

    protected Student studentEntityToStudent(StudentEntity studentEntity) {
        if ( studentEntity == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        student.id( studentEntity.getId() );
        student.studentId( studentEntity.getStudentId() );
        student.major( studentEntity.getMajor() );
        student.name( studentEntity.getName() );

        return student.build();
    }
}
