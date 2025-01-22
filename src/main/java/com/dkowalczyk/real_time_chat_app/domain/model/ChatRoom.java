package com.dkowalczyk.real_time_chat_app.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ChatRoom {
    private final Long id;
    private final String name;
    private final String description;
    private final LocalDateTime createdAt;
    private final boolean isPrivate;
    private final Set<User> participants;
    private final Set<Message> messages;

    public ChatRoom(Long id, String name, String description, LocalDateTime createdAt,
                    boolean isPrivate, Set<User> participants, Set<Message> messages) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.isPrivate = isPrivate;
        this.participants = new HashSet<>(participants);
        this.messages = new HashSet<>(messages);
    }
}
