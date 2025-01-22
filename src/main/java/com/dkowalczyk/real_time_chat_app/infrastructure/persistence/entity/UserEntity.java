package com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDateTime lastseen;

    private boolean online;

    @ManyToMany(mappedBy = "participants")
    private Set<ChatRoomEntity> chatRoomEntities = new HashSet<>();

    @OneToMany(mappedBy = "sender")
    private Set<MessageEntity> messageEntities = new HashSet<>();

    // Konstruktor bezargumentowy wymagany przez JPA
    protected UserEntity() {
    }

    // Konstruktor do tworzenia nowego użytkownika
    public UserEntity(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.lastseen = LocalDateTime.now();
        this.online = false;
    }

    public void updateLastSeen() {
        this.lastseen = LocalDateTime.now();
    }

    public void setOnlineStatus(boolean status) {
        this.online = status;
        if (status) {
            updateLastSeen();
        }
    }
}