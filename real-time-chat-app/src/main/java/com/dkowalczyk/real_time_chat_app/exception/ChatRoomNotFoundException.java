package com.dkowalczyk.real_time_chat_app.exception;

public class ChatRoomNotFoundException extends ChatAppException {
    public ChatRoomNotFoundException(Long id) {
        super(String.format("Chat room not found with id: %d", id));
    }
}