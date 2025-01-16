package com.dkowalczyk.real_time_chat_app.domain.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String username) {
        super(username);
    }
}
