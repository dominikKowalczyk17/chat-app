package com.dkowalczyk.real_time_chat_app.application.service;

import com.dkowalczyk.real_time_chat_app.domain.exception.UnauthorizedAccessException;
import com.dkowalczyk.real_time_chat_app.domain.exception.UserAlreadyExistException;
import com.dkowalczyk.real_time_chat_app.domain.exception.UserNotFoundException;
import com.dkowalczyk.real_time_chat_app.domain.exception.UsernameAlreadyTakenException;
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

import java.time.LocalDateTime;

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
                .orElseThrow(() -> new UserNotFoundException(credentials.email()));

        if (!passwordEncoder.matches(credentials.password(), userEntity.getPassword())) {
            throw new UnauthorizedAccessException("Invalid password");
        }

        userEntity.setOnlineStatus(true);
        userRepository.save(userEntity);

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
        String userEmail = jwtTokenProvider.getEmailFromToken(token);

        UserEntity userEntity = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        userEntity.setOnlineStatus(false);
        userRepository.save(userEntity);
    }

    @Override
    public String refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public AuthenticationResult register(UserCredentials credentials, String username) {
        if (userRepository.findByEmail(credentials.email()).isPresent()) {
            throw new UserAlreadyExistException("User with email " + credentials.email() + " already exists");
        }

        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyTakenException("Username " + username + " already exists");
        }

        UserEntity userEntity = new UserEntity(
                credentials.email(),
                username,
                passwordEncoder.encode(credentials.password())
        );

        UserEntity savedUser = userRepository.save(userEntity);

        String token = jwtTokenProvider.generateToken(savedUser);
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedUser);

        User user = new User(
                (long) savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUsername(),
                savedUser.getLastseen(),
                savedUser.isOnline()
        );

        return new AuthenticationResult(token, refreshToken, user);
    }
}
