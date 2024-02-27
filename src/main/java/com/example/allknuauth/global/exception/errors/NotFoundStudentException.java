package com.example.allknuauth.global.exception.errors;


import com.example.allknuauth.global.exception.ErrorCode;

public class NotFoundStudentException extends RuntimeException {

    public NotFoundStudentException(){
        super(ErrorCode.NOT_FOUND_STUDENT.getMessage());
    }

    public NotFoundStudentException(Exception ex){
        super(ex);
    }
}