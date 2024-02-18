package com.example.allknuauth.student.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByStudentId(String studentId);
}
