package com.example.allknuauth.auth.application.service;

import com.example.allknuauth.auth.application.port.in.LoginMobileUseCase;
import com.example.allknuauth.auth.application.port.out.LoginMobilePort;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginMobileService implements LoginMobileUseCase {

    private final LoginMobilePort loginMobilePort;
    @Override
    public Optional<Map<String, String>> loginMobile(String id, String password) {

        Map<String, String> data = new HashMap<>();
        data.put("user_id", id);
        data.put("user_pwd", password);

        Map<String, String> cookies = loginMobilePort.loginKnuMobile(data);

        return Optional.ofNullable(cookies);
    }
}
