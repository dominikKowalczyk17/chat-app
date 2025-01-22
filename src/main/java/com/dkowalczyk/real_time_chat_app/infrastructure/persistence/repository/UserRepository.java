package com.dkowalczyk.real_time_chat_app.infrastructure.persistence.repository;

import com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByUsernameContaining(String username);

    boolean existsByUsername(String username);
}
