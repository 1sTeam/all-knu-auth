package com.example.allknuauth.consent.application.port.out;

import com.example.allknuauth.consent.domain.Consent;
import org.springframework.stereotype.Component;

@Component
public interface UpdateConsentPort {
    Consent updateConsent(Consent consent);
}
