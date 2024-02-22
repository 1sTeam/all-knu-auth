package com.example.allknuauth.auth.application.port.in;

import java.util.Map;
import java.util.Optional;

public interface LoginVeriusUseCase {
    Optional<Map<String, String>> loginVerius(Map<String, String> ssoCookies);
}
