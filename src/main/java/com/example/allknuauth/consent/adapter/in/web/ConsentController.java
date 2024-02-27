package com.example.allknuauth.consent.adapter.in.web;

import com.example.allknuauth.consent.application.port.in.LoadConsentCommand;
import com.example.allknuauth.consent.application.port.in.LoadConsentUseCase;
import com.example.allknuauth.consent.application.port.in.UpdateConsentCommand;
import com.example.allknuauth.consent.application.port.in.UpdateConsentUseCase;
import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.global.dto.CommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ConsentController {
    private final UpdateConsentUseCase updateConsentUseCase;
    private final LoadConsentUseCase loadConsentUseCase;

    @PutMapping("/students/{studentId}/consents")
    public ResponseEntity<CommonResponse> updateConsents(@PathVariable(name = "studentId") String studentId, @Valid @RequestBody UpdateConsentCommand updateConsentCommand) {
        updateConsentUseCase.updateConsents(studentId, updateConsentCommand);

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("개인정보 수집 성공")
                .build(), HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}/consents")
    public ResponseEntity<CommonResponse> loadConsents(@PathVariable(name = "studentId") String studentId) {
        LoadConsentCommand loadConsentCommand = loadConsentUseCase.loadConsents(studentId);

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("개인정보 수집 동의 여부 조회 성공")
                .list(loadConsentCommand)
                .build(), HttpStatus.OK);
    }
}
