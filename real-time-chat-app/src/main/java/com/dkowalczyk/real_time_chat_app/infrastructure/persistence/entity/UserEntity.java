package com.dkowalczyk.real_time_chat_app.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    private LocalDateTime lastseen;

    private boolean online;

    @ManyToMany(mappedBy = "participants")
    private Set<ChatRoomEntity> chatRoomEntities = new HashSet<>();

    @OneToMany(mappedBy = "sender")
    private Set<MessageEntity> messageEntities = new HashSet<>();
}
