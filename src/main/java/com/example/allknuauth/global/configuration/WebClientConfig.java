package com.example.allknuauth.global.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

//fcm은 사용할 일 없으니 삭제해도 될 듯
@Deprecated
@Configuration
public class WebClientConfig {

    //@Value("${external-client.fcm}")
    //private String fcmUrl;

    //@Bean(name = "fcmClient")
    //public WebClient fcmClient() {
    //    return WebClient.create(fcmUrl);
    //}
}
