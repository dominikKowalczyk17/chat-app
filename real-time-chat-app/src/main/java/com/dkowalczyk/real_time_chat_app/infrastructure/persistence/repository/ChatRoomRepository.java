package com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository;

import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {

    List<ChatRoomEntity> findByParticipantsId(Integer id);

    List<ChatRoomEntity> findByIsPrivate(Boolean isPrivate);

    Optional<ChatRoomEntity> findByName(String name);

    boolean existsByName(String name);


}
