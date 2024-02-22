package com.example.allknuauth.global.exception.errors;

import com.example.allknuauth.global.exception.ErrorCode;

public class RestaurantNameDuplicatedException extends RuntimeException {

    public RestaurantNameDuplicatedException(){
        super(ErrorCode.RESTAURANT_NAME_DUPLICATED.getMessage());
    }

    public RestaurantNameDuplicatedException(Exception ex){
        super(ex);
    }

}
