package com.dkowalczyk.real_time_chat_app.repository;

import com.dkowalczyk.real_time_chat_app.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
