package com.atmaram.ems.service.impl;

import com.atmaram.ems.dto.request.RegisterRequest;
import com.atmaram.ems.dto.response.RegisterResponse;
import com.atmaram.ems.entity.Role;
import com.atmaram.ems.entity.User;
import com.atmaram.ems.enums.RoleType;
import com.atmaram.ems.repository.RoleRepository;
import com.atmaram.ems.repository.UserRepository;
import com.atmaram.ems.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(RoleType.EMPLOYEE)
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(true)
                .role(role)
                .build();

        userRepository.save(user);

        return new RegisterResponse("User registered successfully");
    }
}