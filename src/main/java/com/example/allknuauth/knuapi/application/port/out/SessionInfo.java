package com.example.allknuauth.knuapi.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfo {
    private Map<String, String> mobileCookies;
    private Map<String, String> ssoCookies;
    private Map<String, String> veriusCookies;
}