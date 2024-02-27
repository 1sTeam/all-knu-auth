package com.example.allknuauth.knuapi.application.port.out;

import com.example.allknuauth.consent.domain.ConsentType;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public interface LoadVeriusStudentInfoPort {

    Map<ConsentType, String> loadVeriusStudentInfo(Map<String, String> veriusCookies);
}
