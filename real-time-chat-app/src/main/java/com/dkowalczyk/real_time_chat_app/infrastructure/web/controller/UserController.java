package com.dkowalczyk.real_time_chat_app.infrastructure.web.controller;

import com.dkowalczyk.real_time_chat_app.application.ports.input.UserUseCase;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.request.UpdateUserRequest;
import com.dkowalczyk.real_time_chat_app.infrastructure.web.dto.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserUseCase userUseCase;

    public UserController(final UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody UpdateUserRequest updateRequest
    ) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().build();
        }

        String token = authHeader.substring(7);

        var updatedUser = userUseCase.updateUser(token, updateRequest.username());

        UserResponse userResponse = new UserResponse(
                updatedUser.getId(),
                updatedUser.getEmail(),
                updatedUser.getUsername(),
                updatedUser.isOnline(),
                updatedUser.getLastSeen()
        );

        return ResponseEntity.ok(userResponse);
    }
}
