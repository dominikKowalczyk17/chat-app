package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @NotBlank(message = "Username cannot be blank")
        String username
) {}