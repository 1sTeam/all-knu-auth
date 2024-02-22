package com.example.allknuauth.auth.application.port.out;

import org.jsoup.Connection;

import java.util.Map;

public interface LoginMobilePort {
    Map<String, String> loginKnuMobile(Map<String, String> data);
}
