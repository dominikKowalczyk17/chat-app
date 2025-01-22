package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequest(
        @NotBlank(message = "Token is required")
        String token
) {}
