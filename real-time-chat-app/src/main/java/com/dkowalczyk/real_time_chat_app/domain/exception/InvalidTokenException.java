package com.dkowalczyk.real_time_chat_app.domain.exception;

public class InvalidTokenException extends ChatAppException {
    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
