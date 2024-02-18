package com.example.allknuauth.knuapi.application;

import com.example.allknuauth.knuapi.application.dto.VeriusInfo;

import java.util.Map;

//참인재시스템
public interface KnuVeriusApiService {
    VeriusInfo getStudentInfo(Map<String, String> veriusCookies);
}
