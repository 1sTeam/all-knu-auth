package com.example.allknuauth.global.exception.errors;

import com.example.allknuauth.global.exception.ErrorCode;

public class StationTimeDuplicatedException extends RuntimeException{
    public StationTimeDuplicatedException(){
        super(ErrorCode.STATION_TIME_DUPLICATED.getMessage());
    }

    public StationTimeDuplicatedException(Exception ex){
        super(ex);
    }
}
