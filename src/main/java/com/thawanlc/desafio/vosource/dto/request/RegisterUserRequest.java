package com.thawanlc.desafio.vosource.dto.request;

import com.thawanlc.desafio.vosource.entity.enums.Role;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
    @NotEmpty(message = "Username is required") String username,
    @NotEmpty(message = "Email is required") String email,
    @NotEmpty(message = "Password is required") String password, 
    Role role
) {
    
}
