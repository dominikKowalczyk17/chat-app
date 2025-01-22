package com.dkowalczyk.real_time_chat_app.infrastructure.web.controller;

import com.dkowalczyk.real_time_chat_app.application.ports.input.MessageUseCase;
import com.dkowalczyk.real_time_chat_app.domain.model.Message;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.MessageRepository;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final MessageUseCase messageUseCase;
    private final SimpMessagingTemplate messagingTemplate; // Ensure this is injected

    @MessageMapping("/chat.send")
    public void handleChatMessage(
            @Payload MessageRequest messageRequest,
            Principal principal
    ) {
        Long userId = Long.parseLong(principal.getName());
        log.debug("Broadcasting message to /topic/chat/{}", messageRequest.chatRoomId());

        // Broadcast to subscribers
        messagingTemplate.convertAndSend(
                "/topic/chat/" + messageRequest.chatRoomId(),
                messageRequest
        );
    }
}
