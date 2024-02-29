package com.example.allknuauth.global.exception.errors;

import com.example.allknuauth.global.exception.ErrorCode;

public class NotFoundRestaurantException extends RuntimeException {

    public NotFoundRestaurantException(){
        super(ErrorCode.NOT_FOUND_RESTAURANT.getMessage());
    }

    public NotFoundRestaurantException(Exception ex){
        super(ex);
    }
}
