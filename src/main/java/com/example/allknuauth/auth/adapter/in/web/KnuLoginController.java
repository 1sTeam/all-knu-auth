package com.example.allknuauth.auth.adapter.in.web;

import com.example.allknuauth.auth.application.port.in.GetStudentInfoUseCase;
import com.example.allknuauth.auth.application.port.in.LoginMobileUseCase;
import com.example.allknuauth.auth.application.port.in.LoginSsoUseCase;
import com.example.allknuauth.auth.application.port.in.LoginVeriusUseCase;
import com.example.allknuauth.global.dto.CommonResponse;
import com.example.allknuauth.global.exception.errors.KnuApiCallFailedException;
import com.example.allknuauth.global.exception.errors.KnuReadTimeOutException;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class KnuLoginController {
    private static final Logger logger = LoggerFactory.getLogger(KnuLoginController.class);
    private final LoginMobileUseCase loginMobileUseCase;
    private final LoginSsoUseCase loginSsoUseCase;
    private final LoginVeriusUseCase loginVeriusUseCase;
    private final GetStudentInfoUseCase getStudentInfoUseCase;
    @PostMapping("/knu/login")
    public ResponseEntity<CommonResponse> knuLogin (@Valid @RequestBody KnuLoginResource loginDto) {
        if (loginDto.getId().charAt(0) == '1') {
            logger.info("학번/사번이 1로 시작하는 사람이 로그인 시도");
            throw new LoginFailedException(); // 사번이 1로 시작하는 교직원은 이용불가
        }
        //모바일 로그인
        Map<String, String> mobileCookies = loginMobileUseCase.loginMobile(loginDto.getId(), loginDto.getPassword()).orElseThrow(() -> new LoginFailedException());
        //통합 SSO 로그인
        Map<String, String> ssoCookies = loginSsoUseCase.loginSso(loginDto.getId(), loginDto.getPassword()).orElseThrow(() -> new KnuReadTimeOutException("sso"));
        // 참인재 로그인
        Map<String, String> veriusCookies = loginVeriusUseCase.loginVerius(ssoCookies).orElseThrow(() -> new KnuReadTimeOutException("verius"));
        //학생 정보 긁어오기
        Map<String, String> studentInfo = getStudentInfoUseCase.getStudentInfo(veriusCookies).orElseThrow(() -> new KnuApiCallFailedException());

        // 세션인포 정보 삽입
        Map<String, Object> sessionInfo = new HashMap<>();
        sessionInfo.put("mobileCookies", mobileCookies);
        sessionInfo.put("ssoCookies", ssoCookies);
        sessionInfo.put("veriusCookies", veriusCookies);

        Map<String, Object> responseList = new HashMap<>();
        responseList.put("sessionInfo", sessionInfo);
        responseList.put("studentInfo", studentInfo);

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("로그인 성공")
                .list(responseList)
                .build(), HttpStatus.OK);
    }
}
