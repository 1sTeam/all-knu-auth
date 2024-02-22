package com.example.allknuauth.auth.application.service;

import com.example.allknuauth.auth.application.port.in.GetStudentInfoUseCase;
import com.example.allknuauth.auth.application.port.out.GetStudentInfoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetStudentInfoService implements GetStudentInfoUseCase {

    private final GetStudentInfoPort getStudentInfoPort;
    @Override
    public Optional<Map<String, String>> getStudentInfo(Map<String, String> veriusCookies) {
        Map<String, String> studentInfo = getStudentInfoPort.getKnuStudentInfo(veriusCookies);
        return Optional.ofNullable(studentInfo);
    }
}
