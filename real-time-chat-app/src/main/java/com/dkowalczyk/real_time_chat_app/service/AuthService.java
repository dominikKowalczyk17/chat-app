package com.dkowalczyk.real_time_chat_app.service;

import com.dkowalczyk.real_time_chat_app.dto.request.LoginRequest;
import com.dkowalczyk.real_time_chat_app.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

    void logout(String token);

    String refreshToken(String refreshToken);
}
