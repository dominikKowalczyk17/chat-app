package com.dkowalczyk.real_time_chat_app.infrastructure.web.controller;

import com.dkowalczyk.real_time_chat_app.application.ports.input.AuthUseCase;
import com.dkowalczyk.real_time_chat_app.domain.model.AuthenticationResult;
import com.dkowalczyk.real_time_chat_app.domain.model.UserCredentials;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.AutoLoginRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.LoginRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.RegisterRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.LoginResponse;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AuthUseCase authUseCase;

    public AuthController(final AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserCredentials credentials = new UserCredentials(
                loginRequest.email(),
                loginRequest.password()
        );

        AuthenticationResult result = authUseCase.login(credentials);

        UserResponse userResponse = new UserResponse(
                result.user().getId(),
                result.user().getEmail(),
                result.user().getUsername(),
                result.user().isOnline(),
                result.user().getLastSeen()
        );
        log.info(userResponse.toString());

        return ResponseEntity.ok(new LoginResponse(
                result.token(),
                result.refreshToken(),
                userResponse
        ));
    }

    @PostMapping("/auto-login")
    public ResponseEntity<LoginResponse> autoLogin(@RequestBody AutoLoginRequest request) {
        AuthenticationResult result = authUseCase.autoLogin(request.refreshToken());

        UserResponse userResponse = new UserResponse(
                result.user().getId(),
                result.user().getEmail(),
                result.user().getUsername(),
                result.user().isOnline(),
                result.user().getLastSeen()
        );

        return ResponseEntity.ok(new LoginResponse(
                result.token(),
                result.refreshToken(),
                userResponse
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserCredentials credentials = new UserCredentials(
                registerRequest.email(),
                registerRequest.password()
        );

        AuthenticationResult result = authUseCase.register(credentials, registerRequest.username());

        UserResponse userResponse = new UserResponse(
                result.user().getId(),
                result.user().getEmail(),
                result.user().getUsername(),
                result.user().isOnline(),
                result.user().getLastSeen()
        );

        return ResponseEntity.ok(new LoginResponse(
                result.token(),
                result.refreshToken(),
                userResponse
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            authUseCase.logout(token);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null) {
            return ResponseEntity.badRequest().build();
        }

        String newToken = authUseCase.refreshToken(refreshToken);
        Map<String, String> response = new HashMap<>();
        response.put("token", newToken);

        return ResponseEntity.ok(response);
    }
}
