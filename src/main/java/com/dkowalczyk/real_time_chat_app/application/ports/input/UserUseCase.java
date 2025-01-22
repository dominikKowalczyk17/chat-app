package com.dkowalczyk.real_time_chat_app.application.ports.input;

import com.dkowalczyk.real_time_chat_app.domain.model.User;

public interface UserUseCase {
    User updateUser(String token, String newUsername);
}