package com.example.allknuauth.student.adapter.out.persistence;

import com.example.allknuauth.consent.adapter.out.persistence.ConsentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(mappedBy = "student")
    private List<ConsentEntity> consents = new ArrayList<>();

    @Builder
    public StudentEntity(Long id, String studentId, String name, String major) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.major = major;
    }
}
