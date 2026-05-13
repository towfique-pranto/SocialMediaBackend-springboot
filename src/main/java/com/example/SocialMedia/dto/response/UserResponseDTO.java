package com.example.SocialMedia.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String bio;
}
