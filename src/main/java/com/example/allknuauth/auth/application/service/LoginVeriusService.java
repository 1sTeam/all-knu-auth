package com.example.allknuauth.auth.application.service;

import com.example.allknuauth.auth.application.port.in.LoginVeriusUseCase;
import com.example.allknuauth.auth.application.port.out.LoginVeriusPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginVeriusService implements LoginVeriusUseCase {

    private final LoginVeriusPort loginVeriusPort;
    @Override
    public Optional<Map<String, String>> loginVerius(Map<String, String> ssoCookies) {
        Map<String, String> veriusCookies = loginVeriusPort.loginKnuVerius(ssoCookies);
        return Optional.ofNullable(veriusCookies);
    }
}
