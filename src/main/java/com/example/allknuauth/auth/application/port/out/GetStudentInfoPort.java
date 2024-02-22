package com.example.allknuauth.auth.application.port.out;

import java.util.Map;

public interface GetStudentInfoPort {
    Map<String, String> getKnuStudentInfo(Map<String, String> veriusCookies);
}
