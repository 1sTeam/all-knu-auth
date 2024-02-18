package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.student.adapter.out.persistence.StudentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consent")
@NoArgsConstructor
@Getter
public class ConsentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_name", nullable = false)
    private boolean isName;
    @Column(name = "is_student_id", nullable = false)
    private boolean isStudentId;
    @Column(name = "is_major", nullable = false)
    private boolean isMajor;

    @OneToOne
    @JoinColumn
    private StudentEntity student;

    @Builder
    private ConsentEntity(Long id, boolean isName, boolean isStudentId, boolean isMajor, StudentEntity student) {
        this.id = id;
        this.isName = isName;
        this.isStudentId = isStudentId;
        this.isMajor = isMajor;
        this.student = student;
    }
}
