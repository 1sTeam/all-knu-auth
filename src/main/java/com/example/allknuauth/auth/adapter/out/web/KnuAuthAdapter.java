package com.example.allknuauth.auth.adapter.out.web;

import com.example.allknuauth.auth.application.port.out.*;
import com.example.allknuauth.auth.domain.MajorNoticeType;
import com.example.allknuauth.global.asset.ApiEndpointSecretProperties;
import com.example.allknuauth.global.exception.errors.KnuApiCallFailedException;
import com.example.allknuauth.global.exception.errors.LoginFailedException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KnuAuthAdapter implements GetStudentInfoPort, GetSessionInfoPort {

    private static final Logger logger = LoggerFactory.getLogger(KnuAuthAdapter.class);
    private final ApiEndpointSecretProperties apiEndpointSecretProperties;

    @Override
    public Map<String, Object> getSessionInfo(Map<String, String> data){
        //mobile
        Map<String, String> mobileCookies = null;
        try {
            Connection.Response response = Jsoup.connect(apiEndpointSecretProperties.getMobile().getLogin())
                    .method(Connection.Method.POST)
                    .data(data)
                    .ignoreContentType(true)
                    .userAgent(apiEndpointSecretProperties.getCrawling().getUserAgent())
                    .timeout(5000) // 5초
                    .execute();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.body()); // json mapper

            if (jsonNode.get("result").toString().equals("\"success\"")) {
                mobileCookies = response.cookies();
                logger.info("모바일 로그인 성공");
            } else {
                logger.info("모바일 로그인 실패");
            }
        }
        catch (IOException e){
            logger.error("mobile login error : " + e);
        }

        //sso
        Map<String, String> ssoCookies = null;
        data.put("uid", data.get("user_id"));
        data.remove("user_id");
        data.put("password", data.get("user_pwd"));
        data.remove("user_pwd");
        data.put("gid", "gid_web");
        data.put("returl", apiEndpointSecretProperties.getCrawling().getSsoRetUrl());
        try {
            //ssoLogin jsp를 호출한다.
            Connection.Response ssoLoginRes = Jsoup.connect(apiEndpointSecretProperties.getCrawling().getSsoLogin())
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .data(data)
                    .userAgent(apiEndpointSecretProperties.getCrawling().getUserAgent())
                    .timeout(5000) // 5초
                    .execute();

            ssoCookies = ssoLoginRes.cookies();
            logger.info("sso 로그인 성공");
            //로그인 성공 시 sso_token을 받는다. 이를 통해 로그인 성공 여부 판단
            if (ssoCookies.get("sso_token") == null) {
                //로그인 실패
                logger.info("sso 로그인 실패");
                throw new LoginFailedException();
            }
        } catch (IOException e) {
            logger.error("sso login error : " + e);
        }

        //verius
        Map<String, String> veriusCookies = null;
        try {
            Connection.Response res = Jsoup.connect(apiEndpointSecretProperties.getCrawling().getVeriusSsoLogin())
                    .method(Connection.Method.GET)
                    .cookies(ssoCookies)
                    .ignoreContentType(true)
                    .userAgent(apiEndpointSecretProperties.getCrawling().getUserAgent())
                    .timeout(10000) // 10초
                    .execute();
            veriusCookies = res.cookies();
            logger.info("참인재 로그인 성공");
        } catch (IOException e) {
            logger.error("veriusLogin() error: " + e);
        }

        //sessionInfo
        Map<String, Object> sessionInfo = new HashMap<>();
        sessionInfo.put("mobileCookies", mobileCookies);
        sessionInfo.put("ssoCookies", ssoCookies);
        sessionInfo.put("veriusCookies", veriusCookies);

        return sessionInfo;
    }

    @Override
    public Map<String, String> getStudentInfo(Map<String, String> veriusCookies) {
        String url = apiEndpointSecretProperties.getCrawling().getVeriusStudentInfo() + "?CURRENT_MENU_CODE=MENU0028&TOP_MENU_CODE=MENU0017";
        Map<String, String> result = null;
        try {
            Document res = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .cookies(veriusCookies)
                    .userAgent(apiEndpointSecretProperties.getCrawling().getUserAgent())
                    .get();

            Elements tableViews = res.select("div.tableView tbody"); // tableView tbody를 리스트로 가져온다.
            Elements dataList = tableViews.get(0).select("td"); // 학생 기본정보 테이블의 td들

            result = new HashMap<>();
            result.put("name", dataList.get(0).text());
            result.put("id", dataList.get(1).text());
            result.put("major", dataList.get(3).text()); //전공
            result.put("topic", MajorNoticeType.findByMajor(dataList.get(3).text()).toString());
        } catch (IOException e) {
            logger.error("getStudentInfo IOException error " + e);
            throw new KnuApiCallFailedException();
        } catch (IndexOutOfBoundsException e) {
            logger.error("세션이 유효하지않아 학생정보 조회 실패 " + e);
            throw new KnuApiCallFailedException();
        } catch (Exception e) {
            logger.error("학생정보 조회 시 알수없는 에러 " + e);
            throw new KnuApiCallFailedException();
        }
        return result;
    }
}
