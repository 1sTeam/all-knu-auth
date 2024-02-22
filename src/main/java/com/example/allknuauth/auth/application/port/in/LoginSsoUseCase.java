package com.example.allknuauth.auth.application.port.in;

import java.util.Map;
import java.util.Optional;

public interface LoginSsoUseCase {
    Optional<Map<String, String>> loginSso(String id, String password);
}
