package com.example.allknuauth.consent.application.service;

import com.example.allknuauth.consent.application.port.in.UpdateConsentCommand;
import com.example.allknuauth.consent.application.port.in.UpdateConsentUseCase;
import com.example.allknuauth.consent.application.port.out.LoadConsentPort;
import com.example.allknuauth.consent.application.port.out.UpdateConsentPort;
import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.global.exception.errors.InvalidStudentIdException;
import com.example.allknuauth.global.exception.errors.LoadVeriusStudentInfoFailedException;
import com.example.allknuauth.knuapi.application.port.out.LoadVeriusStudentInfoPort;
import com.example.allknuauth.knuapi.application.port.out.VeriusInfo;
import com.example.allknuauth.student.application.port.out.LoadStudentPort;
import com.example.allknuauth.student.application.port.out.UpdateStudentPort;
import com.example.allknuauth.student.domain.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        VeriusInfo veriusInfo = loadVeriusStudentInfoPort.loadVeriusStudentInfo(command.getSessionInfo().getVeriusCookies());
        if (veriusInfo == null) {
            throw new LoadVeriusStudentInfoFailedException();
        }
        validateStudentId(studentId, veriusInfo);
        Student student = findStudent(studentId);
        updateStudentPort.updateStudent(Student.withId(student.getId(),
                veriusInfo.getStudentId(updateConsentByType(student, ConsentType.STUDENT_ID, command.isStudentId())),
                veriusInfo.getMajor(updateConsentByType(student, ConsentType.MAJOR, command.isMajor())),
                veriusInfo.getName(updateConsentByType(student, ConsentType.NAME, command.isName()))));
    }

    private void validateStudentId(String studentId, VeriusInfo veriusInfo) {
        if (!veriusInfo.getStudentId().equals(studentId)) {
            throw new InvalidStudentIdException();
        }
    }

    private Student findStudent(String studentId) {
        Student student = loadStudentPort.loadStudentByStudentId(studentId);
        if (student == null) {
            student = updateStudentPort.updateStudent(Student.withoutId(studentId, null, null));
        }
        return student;
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

}
