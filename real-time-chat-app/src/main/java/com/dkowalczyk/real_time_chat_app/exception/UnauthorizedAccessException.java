package com.dkowalczyk.real_time_chat_app.exception;

public class UnauthorizedAccessException extends ChatAppException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
