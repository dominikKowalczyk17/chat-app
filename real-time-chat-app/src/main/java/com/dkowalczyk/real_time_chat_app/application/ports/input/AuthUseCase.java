package com.dkowalczyk.real_time_chat_app.application.ports.input;

import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.LoginRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.LoginResponse;

public interface AuthUseCase {
    LoginResponse login(LoginRequest request);

    void logout(String token);

    String refreshToken(String refreshToken);
}
