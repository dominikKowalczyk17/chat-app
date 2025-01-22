package com.dkowalczyk.real_time_chat_app.application.ports.input;

import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.MessageEntity;
import org.springframework.data.domain.Page;

public interface MessageUseCase {

    Page<MessageEntity> getChatRoomMessages(Long chatRoomId, int page, int size);
}
