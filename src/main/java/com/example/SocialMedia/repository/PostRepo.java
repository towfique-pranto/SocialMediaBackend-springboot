package com.example.SocialMedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SocialMedia.entity.PostEntity;

public interface PostRepo extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByActiveTrue();
}
