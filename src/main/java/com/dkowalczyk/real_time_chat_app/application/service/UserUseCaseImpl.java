package com.dkowalczyk.real_time_chat_app.application.service;

import com.dkowalczyk.real_time_chat_app.application.ports.input.UserUseCase;
import com.dkowalczyk.real_time_chat_app.domain.exception.UserNotFoundException;
import com.dkowalczyk.real_time_chat_app.domain.model.User;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.UserEntity;
import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository.UserRepository;
import com.dkowalczyk.real_time_chat_app.security.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUseCaseImpl implements UserUseCase {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserUseCaseImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public User updateUser(String token, String newUsername) {
        String userEmail = jwtTokenProvider.getEmailFromToken(token);

        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + userEmail));

        userEntity.setUsername(newUsername);
        UserEntity savedEntity = userRepository.save(userEntity);

        // Konwertuj UserEntity na User
        return new User(
                (long) savedEntity.getId(),
                savedEntity.getEmail(),
                savedEntity.getUsername(),
                savedEntity.getLastseen(),
                savedEntity.isOnline()
        );
    }
}