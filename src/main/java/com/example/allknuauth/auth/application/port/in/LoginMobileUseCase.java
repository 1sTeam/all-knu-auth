package com.example.allknuauth.auth.application.port.in;

import java.util.Map;
import java.util.Optional;

public interface LoginMobileUseCase {
    Optional<Map<String, String>> loginMobile(String id, String password);
}
