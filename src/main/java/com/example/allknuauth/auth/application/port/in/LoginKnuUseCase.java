package com.example.allknuauth.auth.application.port.in;

import com.example.allknuauth.auth.adapter.in.web.KnuLoginResource;

import java.util.Map;

public interface LoginKnuUseCase {
    Map<String, Object> loginKnu(String id, String password);
}
