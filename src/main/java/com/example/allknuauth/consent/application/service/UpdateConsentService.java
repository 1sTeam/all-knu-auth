package com.example.allknuauth.consent.application.service;

import com.example.allknuauth.consent.application.port.in.UpdateConsentCommand;
import com.example.allknuauth.consent.application.port.in.UpdateConsentUseCase;
import com.example.allknuauth.consent.application.port.out.LoadConsentPort;
import com.example.allknuauth.consent.application.port.out.UpdateConsentPort;
import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.global.exception.errors.InvalidStudentIdException;
import com.example.allknuauth.global.exception.errors.LoadVeriusStudentInfoFailedException;
import com.example.allknuauth.global.exception.errors.NotConsentStudentIdException;
import com.example.allknuauth.knuapi.application.port.out.LoadVeriusStudentInfoPort;
import com.example.allknuauth.student.application.port.out.LoadStudentPort;
import com.example.allknuauth.student.application.port.out.UpdateStudentPort;
import com.example.allknuauth.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateConsentService implements UpdateConsentUseCase {
    private final LoadConsentPort loadConsentPort;
    private final UpdateConsentPort updateConsentPort;
    private final LoadStudentPort loadStudentPort;
    private final UpdateStudentPort updateStudentPort;
    private final LoadVeriusStudentInfoPort loadVeriusStudentInfoPort;

    @Override
    @Transactional
    public void updateConsents(String studentId, UpdateConsentCommand command) {
        if (!command.isStudentId()) {
            throw new NotConsentStudentIdException();
        }
        Map<ConsentType, String> veriusInfo = loadVeriusStudentInfoPort.loadVeriusStudentInfo(command.getSessionInfo().getVeriusCookies());
        if (veriusInfo == null) {
            throw new LoadVeriusStudentInfoFailedException();
        }
        if (!veriusInfo.get(ConsentType.STUDENT_ID).equals(studentId)) {
            throw new InvalidStudentIdException();
        }
        Student student = loadStudentPort.loadStudentByStudentId(studentId);
        if (student == null) {
            student = Student.withoutId(studentId, null, null);
        }
        for (ConsentType type : ConsentType.values()) {
            Consent consent = updateConsentByType(student, type, command.getInfoByConsentType(type));
            student.updateInfo(type, getVeriusInfo(veriusInfo, consent));
        }
        updateStudentPort.updateStudent(student);
    }

    private Consent updateConsentByType(Student student, ConsentType type, boolean value) {
        Consent consent = loadConsentPort.loadConsentByStudentAndType(student, type);
        if (consent == null) {
            consent = Consent.withoutId(type, value, student);
        } else {
            consent = Consent.withId(consent.getId(), type, value, student);
        }
        return updateConsentPort.updateConsent(consent);
    }

    private String getVeriusInfo(Map<ConsentType, String> veriusInfo, Consent consent) {
        if (consent.isValue()) {
            return veriusInfo.get(consent.getType());
        }
        return null;
    }

}
