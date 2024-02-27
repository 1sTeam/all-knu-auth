package com.example.allknuauth.global.exception.errors;


import com.example.allknuauth.global.exception.ErrorCode;

public class NotConsentStudentIdException extends RuntimeException {

    public NotConsentStudentIdException(){
        super(ErrorCode.NOT_CONSENT_STUDENT_ID.getMessage());
    }

    public NotConsentStudentIdException(Exception ex){
        super(ex);
    }
}