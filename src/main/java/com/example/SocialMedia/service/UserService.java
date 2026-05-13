package com.example.SocialMedia.service;

import org.springframework.stereotype.Service;

import com.example.SocialMedia.dto.request.UserRequest;
import com.example.SocialMedia.dto.response.UserResponseDTO;
import com.example.SocialMedia.entity.UserEntity;
import com.example.SocialMedia.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;


    // create user
    public UserResponseDTO createUser(UserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists: " + request.getEmail());
        }

        UserEntity user = UserEntity.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .bio(request.getBio())
                .build();
        UserEntity savedUser = userRepository.save(user);
        return mapToResponseDTO(savedUser);
    }

    // get user
    public UserResponseDTO getUserById(Long id){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToResponseDTO(user);
    }

    // update bio
    public UserResponseDTO updateBio(Long id, UserRequest request){
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setBio(request.getBio());
        UserEntity updatedUser = userRepository.save(user);
        return mapToResponseDTO(updatedUser);
    }

    // mapper
    private UserResponseDTO mapToResponseDTO(UserEntity user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .bio(user.getBio())
                .build();
    }
}
