package com.example.allknuauth.consent.application.port.in;

import org.springframework.stereotype.Component;


@Component
public interface LoadConsentUseCase {
    LoadConsentCommand loadConsents(String studentId);
}
