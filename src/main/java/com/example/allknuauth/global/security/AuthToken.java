package com.example.allknuauth.global.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}
