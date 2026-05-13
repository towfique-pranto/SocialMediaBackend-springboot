package com.example.SocialMedia.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank(message = "must enter fullName")
    private String fullName;

    @NotBlank(message = "must enter email")
    @Email(message = "must add a valid email")
    private String email;

    private String bio;
}
