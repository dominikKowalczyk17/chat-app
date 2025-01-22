package com.dkowalczyk.real_time_chat_app.application.ports.input;

import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.MessageRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.MessageResponse;
import org.springframework.data.domain.Page;

public interface MessageUseCase {

    Page<MessageResponse> getChatRoomMessages(Long chatRoomId, int page, int size);
    MessageResponse sendMessage(MessageRequest message, Long senderId);
    void notifyTyping(Long chatRoomId, Long userId, boolean isTyping);
}
