package com.dkowalczyk.real_time_chat_app.application.ports.input;

import com.dkowalczyk.real_time_chat_app.domain.model.AuthenticationResult;
import com.dkowalczyk.real_time_chat_app.domain.model.UserCredentials;

public interface AuthUseCase {
    AuthenticationResult login(UserCredentials credentials);

    void logout(String email);

    AuthenticationResult autoLogin(String token);

    String refreshToken(String refreshToken);

    AuthenticationResult register(UserCredentials credentials, String username);
}
