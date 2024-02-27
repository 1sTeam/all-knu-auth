package com.example.allknuauth.global.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NOT_FOUND_PATH(HttpStatus.NOT_FOUND, "PATH_001", "NOT FOUND PATH"), // 없는 경로로 요청보낸 경우
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED,"PATH_002","METHOD NOT ALLOWED"), // POST GET방식 잘못 보낸경우
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "PATH_003", "UNSUPPORTED MEDIA TYPE"),

    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED, "AUTH_001", " AUTHENTICATION_FAILED."),
    LOGIN_FAILED(HttpStatus.NOT_FOUND, "AUTH_002", " LOGIN_FAILED."),
    KNU_READ_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "AUTH_003", "KNU 서버가 응답이 없습니다"), // 강남대서버가 응답이 없을 때
    REGISTER_FAILED(HttpStatus.FORBIDDEN, "AUTH_003", "REGISTER_FAILED"),
    REQUEST_PARAMETER_BIND_FAILED(HttpStatus.BAD_REQUEST, "REQ_001", "PARAMETER_BIND_FAILED"),
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH004", "INVALID_JWT_TOKEN."),
    KNU_API_FAILED(HttpStatus.FORBIDDEN, "AUTH_005", "knu api call failed"),
    INVALID_STUDENT_ID(HttpStatus.BAD_REQUEST, "STUDENT_001", "유효하지 않은 학번임"),
    LOAD_VERIUS_STUDENT_INFO_FAILED(HttpStatus.NOT_FOUND, "STUDENT_002", "참인재 학생 정보 불러오기 실패"),
    NOT_CONSENT_STUDENT_ID(HttpStatus.BAD_REQUEST, "STUDENT_003", "학번 수집 거부 불가"),
    NOT_FOUND_STUDENT(HttpStatus.BAD_REQUEST, "STUDENT_004", "학생 정보가 없음"),

    FCM_CLIENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "FCM_001", "FCM Failed"),
    ;

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(final HttpStatus status, final String code, final String message){
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
