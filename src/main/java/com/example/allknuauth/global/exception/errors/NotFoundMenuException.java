package com.example.allknuauth.global.exception.errors;

import com.example.allknuauth.global.exception.ErrorCode;

public class NotFoundMenuException extends RuntimeException{
    public NotFoundMenuException(){
        super(ErrorCode.NOT_FOUND_MENU.getMessage());
    }

    public NotFoundMenuException(Exception ex){
        super(ex);
    }
}
