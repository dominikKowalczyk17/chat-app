package com.dkowalczyk.real_time_chat_app.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final Long id;
    private final String email;
    private final String username;
    private final LocalDateTime lastSeen;
    private final boolean online;
    private final Set<ChatRoom> chatRooms;
    private final Set<Message> messages;

    // Konstruktor dla nowego użytkownika
    public User(Long id, String email, String username, LocalDateTime lastSeen,
                boolean online, Set<ChatRoom> chatRooms, Set<Message> messages) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.lastSeen = lastSeen;
        this.online = online;
        this.chatRooms = new HashSet<>(chatRooms);
        this.messages = new HashSet<>(messages);
    }

    // Konstruktor dla istniejącego użytkownika
    public User(String email, String username) {
        this.id = null;
        this.email = email;
        this.username = username;
        this.lastSeen = LocalDateTime.now();
        this.online = false;
        this.chatRooms = new HashSet<>();
        this.messages = new HashSet<>();
    }

    // Konstruktor dla logowania
    public User(Long id, String email, String username, LocalDateTime lastSeen, boolean online) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.lastSeen = lastSeen;
        this.online = online;
        this.chatRooms = new HashSet<>();
        this.messages = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public boolean isOnline() {
        return online;
    }

    public Set<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public Set<Message> getMessages() {
        return messages;
    }
}
