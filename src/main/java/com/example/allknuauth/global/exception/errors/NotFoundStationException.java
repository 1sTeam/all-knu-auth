package com.example.allknuauth.global.exception.errors;

import com.example.allknuauth.global.exception.ErrorCode;

public class NotFoundStationException extends RuntimeException{
    public NotFoundStationException(){
        super(ErrorCode.NOT_FOUND_STATION.getMessage());
    }

    public NotFoundStationException(Exception ex){
        super(ex);
    }
}
