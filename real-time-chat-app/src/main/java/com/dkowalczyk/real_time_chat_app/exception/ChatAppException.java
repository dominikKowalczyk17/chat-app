package com.dkowalczyk.real_time_chat_app.exception;

public abstract class ChatAppException extends RuntimeException {
    protected ChatAppException(String message) {
        super(message);
    }

    protected ChatAppException(String message, Throwable cause) {
        super(message, cause);
    }
}

