package com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response;

import java.time.LocalDateTime;

public record MessageResponse(
        Long id,
        String content,
        LocalDateTime sentAt,
        LocalDateTime editedAt,
        boolean isEdited,
        Long senderId,
        Long chatRoomId
) {}
