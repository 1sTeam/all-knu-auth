package com.example.allknuauth.knuapi.application.port.out;

import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface LoadVeriusStudentInfoPort {

    VeriusInfo loadVeriusStudentInfo(Map<String, String> veriusCookies);
}
