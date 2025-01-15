package com.dkowalczyk.real_time_chat_app.application.service;

import com.dkowalczyk.real_time_chat_app.domain.model.AuthenticationResult;
import com.dkowalczyk.real_time_chat_app.domain.model.User;
import com.dkowalczyk.real_time_chat_app.domain.model.UserCredentials;
import com.dkowalczyk.real_time_chat_app.application.ports.input.AuthUseCase;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.UserEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.UserRepository;
import com.dkowalczyk.real_time_chat_app.security.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUseCaseImpl implements AuthUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthenticationResult login(UserCredentials credentials) {
        UserEntity userEntity = userRepository.findByEmail(credentials.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(credentials.password(), userEntity.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        String token = jwtTokenProvider.generateToken(userEntity);
        String refreshedToken = jwtTokenProvider.generateRefreshToken(userEntity);

        User user = new User(
                (long) userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getLastseen(),
                userEntity.isOnline()
        );

        return new AuthenticationResult(token, refreshedToken, user);
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public String refreshToken(String refreshToken) {
        return null;
    }
}
