package com.dkowalczyk.real_time_chat_app.domain.exception;

public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {}
