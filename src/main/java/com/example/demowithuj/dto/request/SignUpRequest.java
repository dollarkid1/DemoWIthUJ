package com.example.demowithuj.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignUpRequest {

    private String phoneNumber;

    @NotBlank(message = "phone number cannot be empty")
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;
    private Set<String> roles;
}
