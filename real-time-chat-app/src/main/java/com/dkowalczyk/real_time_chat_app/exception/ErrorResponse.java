package com.dkowalczyk.real_time_chat_app.exception;

public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {}
