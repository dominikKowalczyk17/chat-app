package com.dkowalczyk.real_time_chat_app.application.service;

import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.LoginRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.LoginResponse;
import com.dkowalczyk.real_time_chat_app.application.ports.input.AuthUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUseCaseImpl implements AuthUseCase {

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public String refreshToken(String refreshToken) {
        return null;
    }
}
