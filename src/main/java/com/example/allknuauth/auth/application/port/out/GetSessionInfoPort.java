package com.example.allknuauth.auth.application.port.out;

import java.util.Map;

public interface GetSessionInfoPort {
    Map<String, Object> getSessionInfo(Map<String, String> data);
}
