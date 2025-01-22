package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MessageRequest(
        @NotNull(message = "Chat room ID is required")
        Long chatRoomId,

        @NotBlank(message = "Message content cannot be empty")
        @Size(min = 1, max = 1000, message = "Message must be between 1 and 1000 characters")
        String content
) {
}
