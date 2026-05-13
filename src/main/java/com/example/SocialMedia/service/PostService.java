package com.example.SocialMedia.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.SocialMedia.dto.request.PostRequestDTO;
import com.example.SocialMedia.dto.response.PostResponseDTO;
import com.example.SocialMedia.entity.PostEntity;
import com.example.SocialMedia.repository.PostRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepo postRepository;

    public PostResponseDTO createPost(PostRequestDTO request){
        PostEntity post = PostEntity.builder()
                .authorName(request.getAuthorName())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        PostEntity savedPost = postRepository.save(post);
        return mapToResponseDTO(savedPost);
    }

    public List<PostResponseDTO> getAllActivePosts(){
        List<PostEntity> posts = postRepository.findAllByActiveTrue();
        return posts.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public void deletePost(Long id){
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));

        post.setActive(false);
        postRepository.save(post);
    }

    private PostResponseDTO mapToResponseDTO(PostEntity post){
        return PostResponseDTO.builder()
                .id(post.getId())
                .authorName(post.getAuthorName())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
