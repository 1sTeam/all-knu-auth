package com.example.allknuauth.student.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Getter
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id", nullable = false)
    private String studentId;
    @Column(name = "name")
    private String name;
    @Column(name = "major")
    private String major;


    @Builder
    public StudentEntity(Long id, String studentId, String name, String major) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.major = major;
    }
}
