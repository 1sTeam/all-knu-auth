package com.example.allknuauth.consent.application.port.in;

import com.example.allknuauth.knuapi.application.dto.SessionInfo;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Getter
public class UpdateConsentCommand {
    @NotNull(message = "강남대 쿠키 넣어주세요")
    private SessionInfo sessionInfo;
    private boolean name;
    @NotNull(message = "학번 수집 동의 여부 비었습니다")
    private boolean studentId;
    private boolean major;

    public UpdateConsentCommand(SessionInfo sessionInfo, Boolean name, Boolean studentId, Boolean major) {
        this.sessionInfo = sessionInfo;
        this.name = name;
        this.studentId = studentId;
        this.major = major;
    }
}
