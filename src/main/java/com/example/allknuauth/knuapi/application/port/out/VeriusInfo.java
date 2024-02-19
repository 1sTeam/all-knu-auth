package com.example.allknuauth.knuapi.application.port.out;

import com.example.allknuauth.consent.domain.Consent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class VeriusInfo {
    private String name;
    private String studentId;
    private String major;


    public VeriusInfo(String name, String studentId, String major) {
        this.name = name;
        this.studentId = studentId;
        this.major = major;
    }

    public String getStudentId(Consent consent) {
        if (consent.isValue()) {
            return this.studentId;
        }
        return null;
    }

    public String getMajor(Consent consent) {
        if (consent.isValue()) {
            return this.major;
        }
        return null;
    }

    public String getName(Consent consent) {
        if (consent.isValue()) {
            return this.name;
        }
        return null;
    }
}
