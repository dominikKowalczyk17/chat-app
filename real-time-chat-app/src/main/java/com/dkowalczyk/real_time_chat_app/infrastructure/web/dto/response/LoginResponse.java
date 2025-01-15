package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response;

public record LoginResponse(
        String token,
        String refreshToken,
        UserResponse user
) {
}
