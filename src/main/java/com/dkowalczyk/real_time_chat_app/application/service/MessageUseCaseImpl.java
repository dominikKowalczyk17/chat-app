package com.dkowalczyk.real_time_chat_app.application.service;

import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.MessageEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.MessageRepository;
import com.dkowalczyk.real_time_chat_app.application.ports.input.MessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class MessageUseCaseImpl implements MessageUseCase {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageUseCaseImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Page<MessageEntity> getChatRoomMessages(Long chatRoomId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").descending());
        return messageRepository.findByChatRoomId(chatRoomId, pageable);
    }
}
