package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response;

import com.dkowalczyk.real_time_chat_app.domain.enums.TypingStatus;

public record TypingResponse(
        Long chatRoomId,
        Long userId,
        TypingStatus status
) {
}
