package com.dkowalczyk.real_time_chat_app.application.ports.output;

import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.MessageEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.MessageRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.MessageResponse;

public interface MessageMapper {
    MessageEntity toEntity(MessageRequest request, Long senderId);
    MessageResponse toResponse(MessageEntity entity);
}
