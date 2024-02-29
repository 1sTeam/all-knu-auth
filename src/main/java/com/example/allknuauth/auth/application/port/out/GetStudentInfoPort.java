package com.example.allknuauth.auth.application.port.out;

import java.util.Map;

public interface GetStudentInfoPort {
    Map<String, String> getStudentInfo(Map<String, String> veriusCookies);
}
