package com.dkowalczyk.real_time_chat_app.domain.model;

import java.time.LocalDateTime;

public class Message {

    private final Long id;
    private final String content;
    private final LocalDateTime sentAt;
    private final LocalDateTime editedAt;
    private final boolean isEdited;
    private final User sender;
    private final ChatRoom chatRoom;

    public Message(Long id, String content, LocalDateTime sentAt, LocalDateTime editedAt,
                   boolean isEdited, User sender, ChatRoom chatRoom) {
        this.id = id;
        this.content = content;
        this.sentAt = sentAt;
        this.editedAt = editedAt;
        this.isEdited = isEdited;
        this.sender = sender;
        this.chatRoom = chatRoom;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public LocalDateTime getEditedAt() {
        return editedAt;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public User getSender() {
        return sender;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }
}
