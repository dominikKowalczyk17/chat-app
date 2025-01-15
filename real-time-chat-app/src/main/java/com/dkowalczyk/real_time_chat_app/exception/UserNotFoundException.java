package com.dkowalczyk.real_time_chat_app.exception;

public class UserNotFoundException extends ChatAppException {
    public UserNotFoundException(Long id) {
        super(String.format("User not found with id: %d", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("User not found with email: %s", email));
    }
}