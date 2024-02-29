package com.example.allknuauth.auth.adapter.in.web;

import com.example.allknuauth.auth.application.port.in.*;
import com.example.allknuauth.global.dto.CommonResponse;
import com.example.allknuauth.global.exception.errors.LoginFailedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KnuLoginController {
    private static final Logger logger = LoggerFactory.getLogger(KnuLoginController.class);
    private final LoginKnuUseCase loginKnuUseCase;

    @PostMapping("/knu/login")
    public ResponseEntity<CommonResponse> knuLogin (@Valid @RequestBody KnuLoginResource loginDto) {
        if (loginDto.getId().charAt(0) == '1') {
            logger.info("학번/사번이 1로 시작하는 사람이 로그인 시도");
            throw new LoginFailedException(); // 사번이 1로 시작하는 교직원은 이용불가
        }

        Map<String, Object> responseList = loginKnuUseCase.loginKnu(loginDto.getId(), loginDto.getPassword());

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("로그인 성공")
                .list(responseList)
                .build(), HttpStatus.OK);
    }
}
