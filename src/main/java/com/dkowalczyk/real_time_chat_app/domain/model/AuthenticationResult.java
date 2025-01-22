package com.dkowalczyk.real_time_chat_app.domain.model;

public record AuthenticationResult(
        String token,
        String refreshToken,
        User user
) {}
