package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsentRepository extends JpaRepository<ConsentEntity, Long> {
    ConsentEntity findByStudent(Student student);
}
