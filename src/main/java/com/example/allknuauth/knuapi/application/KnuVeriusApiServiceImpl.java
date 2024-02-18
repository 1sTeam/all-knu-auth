package com.example.allknuauth.knuapi.application;

import com.example.allknuauth.global.asset.ApiEndpointSecretProperties;
import com.example.allknuauth.global.exception.errors.KnuApiCallFailedException;
import com.example.allknuauth.knuapi.application.dto.VeriusInfo;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.LoggerFactory;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;

@Service
@RequiredArgsConstructor
public class KnuVeriusApiServiceImpl implements KnuVeriusApiService {
    private static final Logger logger = LoggerFactory.getLogger(KnuVeriusApiServiceImpl.class);
    private final ApiEndpointSecretProperties apiEndpointSecretProperties;

    @Override
    public VeriusInfo getStudentInfo(Map<String, String> veriusCookies) {
        //참인재시스템에서 학과, 학번, 이름 등 학생 정보를 긁어다 준다.
        //해당 참인재 쿠키로 정보를 긁어온다.
        String url = apiEndpointSecretProperties.getCrawling().getVeriusStudentInfo() + "?CURRENT_MENU_CODE=MENU0028&TOP_MENU_CODE=MENU0017";
        try {
            Document res = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .cookies(veriusCookies)
                    .userAgent(apiEndpointSecretProperties.getCrawling().getUserAgent())
                    .get();

            Elements tableViews = res.select("div.tableView tbody"); // tableView tbody를 리스트로 가져온다.
            Elements dataList = tableViews.get(0).select("td"); // 학생 기본정보 테이블의 td들
            return new VeriusInfo(dataList.get(0).text(), dataList.get(1).text(), dataList.get(3).text());
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
    }

}