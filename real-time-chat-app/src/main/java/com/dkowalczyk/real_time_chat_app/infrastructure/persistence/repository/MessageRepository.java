package com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository;

import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.MessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findByChatRoomId(Long chatRoomId);

    List<MessageEntity> findBySenderId(Long senderId);

    List<MessageEntity> findByChatRoomIdOrderBySentAtDesc(Long chatRoomId);


    // For pagination:
    Page<MessageEntity> findByChatRoomId(Long chatRoomId, Pageable pageable);

    // For getting messages after a certain date
    List<MessageEntity> findByChatRoomIdAndSentAtAfter(Long chatRoomId, LocalDateTime date);
}
