package com.example.allknuauth.global.exception.errors;


import com.example.allknuauth.global.exception.ErrorCode;

public class StationNameDuplicatedException extends RuntimeException {

    public StationNameDuplicatedException(){
        super(ErrorCode.STATION_NAME_DUPLICATED.getMessage());
    }

    public StationNameDuplicatedException(Exception ex){
        super(ex);
    }
}