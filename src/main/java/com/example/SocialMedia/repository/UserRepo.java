package com.example.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SocialMedia.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
}
