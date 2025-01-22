package com.dkowalczyk.real_time_chat_app.domain.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super(email);
    }
}
