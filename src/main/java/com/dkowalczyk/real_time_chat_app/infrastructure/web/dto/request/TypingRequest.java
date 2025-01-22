package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request;

import com.dkowalczyk.real_time_chat_app.domain.enums.TypingStatus;

public record TypingRequest(
        Long chatRoomId,
        TypingStatus typingRequest
) {}


