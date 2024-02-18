package com.example.allknuauth.global.exception.errors;


import com.example.allknuauth.global.exception.ErrorCode;

public class LoadVeriusStudentInfoFailedException extends RuntimeException {

    public LoadVeriusStudentInfoFailedException(){
        super(ErrorCode.LOAD_VERIUS_STUDENT_INFO_FAILED.getMessage());
    }

    public LoadVeriusStudentInfoFailedException(Exception ex){
        super(ex);
    }
}