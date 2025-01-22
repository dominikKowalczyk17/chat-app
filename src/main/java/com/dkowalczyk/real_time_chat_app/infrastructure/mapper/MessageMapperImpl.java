package com.dkowalczyk.real_time_chat_app.infrastructure.mapper;

import com.dkowalczyk.real_time_chat_app.application.ports.output.MessageMapper;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.ChatRoomEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.MessageEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.UserEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.ChatRoomRepository;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.UserRepository;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.MessageRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageMapperImpl implements MessageMapper {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public MessageMapperImpl(UserRepository userRepository, ChatRoomRepository chatRoomRepository) {
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public MessageResponse toResponse(MessageEntity entity) {
        return new MessageResponse(
                entity.getId(),
                entity.getContent(),
                entity.getSentAt(),
                entity.getEditedAt(),
                entity.isEdited(),
                entity.getSender().getId(),
                entity.getChatRoom().getId()
        );

    }

    @Override
    public MessageEntity toEntity(MessageRequest request, Long senderId) {
        UserEntity sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ChatRoomEntity chatRoom = chatRoomRepository.findById(request.chatRoomId())
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(request.content());
        messageEntity.setSender(sender);
        messageEntity.setChatRoom(chatRoom);
        messageEntity.setSentAt(LocalDateTime.now());
        messageEntity.setEdited(false);

        return messageEntity;
    }
}
