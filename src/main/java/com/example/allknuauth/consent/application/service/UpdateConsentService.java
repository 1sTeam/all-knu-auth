package com.example.allknuauth.consent.application.service;

import com.example.allknuauth.consent.application.port.in.UpdateConsentCommand;
import com.example.allknuauth.consent.application.port.in.UpdateConsentUseCase;
import com.example.allknuauth.consent.application.port.out.LoadConsentPort;
import com.example.allknuauth.consent.application.port.out.UpdateConsentPort;
import com.example.allknuauth.consent.domain.Consent;
import com.example.allknuauth.global.exception.errors.InvalidStudentIdException;
import com.example.allknuauth.global.exception.errors.LoadVeriusStudentInfoFailedException;
import com.example.allknuauth.knuapi.application.KnuVeriusApiService;
import com.example.allknuauth.knuapi.application.dto.VeriusInfo;
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
    private final KnuVeriusApiService knuVeriusApiService;

    @Override
    @Transactional
    public void updateConsents(String studentId, UpdateConsentCommand command) {
        VeriusInfo veriusInfo = knuVeriusApiService.getStudentInfo(command.getSessionInfo().getVeriusCookies());
        if (veriusInfo == null){
            throw new LoadVeriusStudentInfoFailedException();
        }
        validateStudentId(studentId, veriusInfo);
        Student student = findStudent(studentId);
        Consent consent = updateConsent(student, command);
        updateStudentPort.updateStudent(Student.withId(student.getId(), veriusInfo.getStudentId(consent), veriusInfo.getMajor(consent), veriusInfo.getName(consent)));
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

    private Consent updateConsent(Student student, UpdateConsentCommand command) {
        Consent consent = loadConsentPort.loadConsentByStudent(student);
        if (consent == null) {
            consent = Consent.withoutId(command.isStudentId(), command.isName(), command.isMajor(), student);
        } else {
            consent = Consent.withId(consent.getId(), command.isStudentId(), command.isName(), command.isMajor(), student);
        }
        return updateConsentPort.updateConsent(consent);
    }

}
