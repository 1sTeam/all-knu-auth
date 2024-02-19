package com.example.allknuauth.consent.adapter.out.persistence;

import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.student.adapter.out.persistence.StudentEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ConsentType type;
    @Column(name = "value")
    private boolean value;
    @ManyToOne
    @JoinColumn
    private StudentEntity student;


    @Builder
    private ConsentEntity(Long id, ConsentType type, boolean value, StudentEntity student) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.student = student;
    }
}
