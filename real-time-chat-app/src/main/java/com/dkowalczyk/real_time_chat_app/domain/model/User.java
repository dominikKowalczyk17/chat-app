package com.dkowalczyk.real_time_chat_app.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final Integer id;
    private final String email;
    private final String username;
    private final LocalDateTime lastSeen;
    private final boolean online;
    private final Set<ChatRoom> chatRooms;
    private final Set<Message> messages;

    public User(Integer id, String email, String username, LocalDateTime lastSeen,
                boolean online, Set<ChatRoom> chatRooms, Set<Message> messages) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.lastSeen = lastSeen;
        this.online = online;
        this.chatRooms = new HashSet<>(chatRooms);
        this.messages = new HashSet<>(messages);
    }
}
