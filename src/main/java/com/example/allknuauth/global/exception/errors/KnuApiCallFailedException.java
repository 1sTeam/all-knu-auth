package com.example.allknuauth.global.exception.errors;


import com.example.allknuauth.global.exception.ErrorCode;

public class KnuApiCallFailedException extends RuntimeException {

    public KnuApiCallFailedException(){
        super(ErrorCode.KNU_API_FAILED.getMessage());
    }

    public KnuApiCallFailedException(Exception ex){
        super(ex);
    }
}
