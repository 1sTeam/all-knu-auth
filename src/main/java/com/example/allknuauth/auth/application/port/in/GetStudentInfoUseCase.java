package com.example.allknuauth.auth.application.port.in;

import java.util.Map;
import java.util.Optional;

public interface GetStudentInfoUseCase {
    Optional<Map<String, String>> getStudentInfo(Map<String, String> veriusCookies);
}
