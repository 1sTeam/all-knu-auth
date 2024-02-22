package com.example.allknuauth.auth.application.port.out;

import java.util.Map;

public interface LoginVeriusPort {
    Map<String, String> loginKnuVerius(Map<String, String> ssoCookies);
}
