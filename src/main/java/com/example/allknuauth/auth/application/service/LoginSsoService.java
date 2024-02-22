package com.example.allknuauth.auth.application.service;

import com.example.allknuauth.auth.application.port.in.LoginSsoUseCase;
import com.example.allknuauth.auth.application.port.out.LoginSsoPort;
import com.example.allknuauth.global.asset.ApiEndpointSecretProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginSsoService implements LoginSsoUseCase {

    private final LoginSsoPort loginSsoPort;
    private final ApiEndpointSecretProperties apiEndpointSecretProperties;

    @Override
    public Optional<Map<String, String>> loginSso(String id, String password) {

        Map<String, String> data = new HashMap<>();
        data.put("uid", id);
        data.put("password", password);
        data.put("gid", "gid_web");
        data.put("returl", apiEndpointSecretProperties.getCrawling().getSsoRetUrl());

        Map<String, String> cookies = loginSsoPort.loginKnuSso(data);

        return Optional.ofNullable(cookies);
    }
}
