package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String username,
        boolean online,
        LocalDateTime date
) {
}
