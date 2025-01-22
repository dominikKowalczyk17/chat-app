package com.dkowalczyk.real_time_chat_app.application.service;

import com.dkowalczyk.real_time_chat_app.application.ports.output.MessageMapper;
import com.dkowalczyk.real_time_chat_app.application.ports.output.MessagePublisher;
import com.dkowalczyk.real_time_chat_app.domain.enums.TypingStatus;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.MessageEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.MessageRepository;
import com.dkowalczyk.real_time_chat_app.application.ports.input.MessageUseCase;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.MessageRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.MessageResponse;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.TypingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MessageUseCaseImpl implements MessageUseCase {
    private final MessageRepository messageRepository;
    private final MessagePublisher messagePublisher;
    private final MessageMapper messageMapper;

    public MessageUseCaseImpl(
            MessageRepository messageRepository,
            MessagePublisher messagePublisher,
            MessageMapper messageMapper
    ) {
        this.messageRepository = messageRepository;
        this.messagePublisher = messagePublisher;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageResponse sendMessage(MessageRequest message, Long senderId) {
        MessageEntity entity = messageMapper.toEntity(message, senderId);
        entity = messageRepository.save(entity);

        MessageResponse response = messageMapper.toResponse(entity);
        messagePublisher.publishMessage(response, message.chatRoomId());

        return response;
    }

    @Override
    public void notifyTyping(Long chatRoomId, Long userId, boolean isTyping) {
        TypingResponse typingResponse = new TypingResponse(
                chatRoomId,
                userId,
                isTyping ? TypingStatus.STARTED : TypingStatus.STOPPED
        );
        messagePublisher.publishTypingEvent(typingResponse);
    }

    @Override
    public Page<MessageResponse> getChatRoomMessages(Long chatRoomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").descending());
        Page<MessageEntity> messageEntities = messageRepository.findByChatRoomId(chatRoomId, pageable);
        return messageEntities.map(messageMapper::toResponse);
    }
}
