package com.thawanlc.desafio.vosource.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thawanlc.desafio.vosource.config.TokenConfig;
import com.thawanlc.desafio.vosource.dto.request.LoginRequest;
import com.thawanlc.desafio.vosource.dto.request.RegisterUserRequest;
import com.thawanlc.desafio.vosource.dto.response.LoginResponse;
import com.thawanlc.desafio.vosource.dto.response.RegisterUserResponse;
import com.thawanlc.desafio.vosource.entity.User;
import com.thawanlc.desafio.vosource.entity.enums.Role;
import com.thawanlc.desafio.vosource.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    public AuthenticationManager authenticationManager;
    private TokenConfig tokenConfig;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(authToken);
        
        User user = (User) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        
        return authentication.isAuthenticated() ? ResponseEntity.ok(new LoginResponse(user.getUsername(), token)) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        if(request.role() != null) {
            user.setRoles(Set.of(request.role()));
        } else {
            user.setRoles(Set.of(Role.OPERADOR));
        }

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(user.getUsername(), user.getEmail()));
    }
    

}
