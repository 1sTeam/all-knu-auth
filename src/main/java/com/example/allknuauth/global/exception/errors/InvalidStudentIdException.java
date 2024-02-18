package com.example.allknuauth.global.exception.errors;
import com.example.allknuauth.global.exception.ErrorCode;
public class InvalidStudentIdException extends RuntimeException{
    public InvalidStudentIdException(){
        super(ErrorCode.INVALID_STUDENT_ID.getMessage());
    }

    public InvalidStudentIdException(Exception ex){
        super(ex);
    }
}
