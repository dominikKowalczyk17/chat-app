package com.dkowalczyk.real_time_chat_app.infrastructure.web.controller;

import com.dkowalczyk.real_time_chat_app.application.ports.input.MessageUseCase;
import com.dkowalczyk.real_time_chat_app.domain.model.Message;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.MessageRepository;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    public MessageUseCase messageUseCase;

    public ChatController(MessageUseCase messageUseCase) {
        this.messageUseCase = messageUseCase;
    }

    @MessageMapping("/chat.send")
    public void handleChatMessage(
            @Payload MessageRequest messageRequest,
            @AuthenticationPrincipal UserDetails userDetails,
            Principal principal
    ) {
        Long userId = Long.parseLong(principal.getName());
        log.debug("Handling chat message from user: {}", userId);
        messageUseCase.sendMessage(messageRequest, userId);
    }
}
