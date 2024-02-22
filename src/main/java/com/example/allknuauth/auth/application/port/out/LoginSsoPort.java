package com.example.allknuauth.auth.application.port.out;

import java.util.Map;

public interface LoginSsoPort {
    Map<String, String> loginKnuSso(Map<String, String> data);
}
