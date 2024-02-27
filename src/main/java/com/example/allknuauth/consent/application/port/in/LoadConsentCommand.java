package com.example.allknuauth.consent.application.port.in;

import com.example.allknuauth.consent.domain.ConsentType;
import com.example.allknuauth.knuapi.application.port.out.SessionInfo;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

import static com.example.allknuauth.consent.domain.ConsentType.NAME;
import static com.example.allknuauth.consent.domain.ConsentType.STUDENT_ID;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Getter
public class LoadConsentCommand {
    private Map<ConsentType, Boolean> consents;

    public LoadConsentCommand(Map<ConsentType, Boolean> consents) {
        this.consents = consents;
    }
}
