package com.example.allknuauth.auth.application.service;

import com.example.allknuauth.auth.application.port.in.LoginKnuUseCase;
import com.example.allknuauth.auth.application.port.out.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginKnuService implements LoginKnuUseCase {

    private final GetSessionInfoPort getSessionInfoPort;
    private final GetStudentInfoPort getStudentInfoPort;
    @Override
    public Map<String, Object> loginKnu(String id, String password) {
        //get sessionInfo
        Map<String, String> data = new HashMap<>();
        data.put("user_id", id);
        data.put("user_pwd", password);
        Map<String, Object> sessionInfo = getSessionInfoPort.getSessionInfo(data);

        //get studentInfo
        Map<String, String> studentInfo = getStudentInfoPort.getStudentInfo((Map<String, String>) sessionInfo.get("veriusCookies"));

        Map<String, Object> responseList = new HashMap<>();
        responseList.put("sessionInfo", sessionInfo);
        responseList.put("studentInfo", studentInfo);

        return responseList;
    }
}
