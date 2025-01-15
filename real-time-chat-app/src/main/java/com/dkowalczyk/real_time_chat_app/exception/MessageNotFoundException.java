package com.dkowalczyk.real_time_chat_app.exception;

public class MessageNotFoundException extends ChatAppException {
    public MessageNotFoundException(Long id) {
        super(String.format("Message not found with id: %d", id));
    }
}