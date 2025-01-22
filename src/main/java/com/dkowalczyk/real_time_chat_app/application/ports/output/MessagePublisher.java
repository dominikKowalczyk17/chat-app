package com.dkowalczyk.real_time_chat_app.application.ports.output;

import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.MessageResponse;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.TypingResponse;

public interface MessagePublisher {
    void publishMessage(MessageResponse message, Long chatRoomId);
    void publishTypingEvent(TypingResponse typingEvent);
}
