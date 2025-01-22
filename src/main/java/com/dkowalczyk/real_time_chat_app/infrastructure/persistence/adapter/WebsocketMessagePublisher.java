package com.dkowalczyk.real_time_chat_app.infrastructure.persistence.adapter;

import com.dkowalczyk.real_time_chat_app.application.ports.output.MessagePublisher;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.MessageResponse;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.TypingResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebsocketMessagePublisher implements MessagePublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public WebsocketMessagePublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void publishMessage(MessageResponse message, Long chatRoomId) {
        messagingTemplate.convertAndSend(
                "/topic/chat/" + chatRoomId,
                message
        );
    }

    @Override
    public void publishTypingEvent(TypingResponse typingEvent) {
        messagingTemplate.convertAndSend(
                "/topic/chat" + typingEvent.chatRoomId() + "/typing", typingEvent);
    }
}
