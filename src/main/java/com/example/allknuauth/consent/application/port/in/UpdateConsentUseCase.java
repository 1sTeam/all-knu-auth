package com.example.allknuauth.consent.application.port.in;

import org.springframework.stereotype.Component;

@Component
public interface UpdateConsentUseCase {
    void updateConsents(String studentId, UpdateConsentCommand updateConsentCommand);
}
