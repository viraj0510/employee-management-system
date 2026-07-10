package com.atmaram.ems.service.impl;


import com.atmaram.ems.dto.request.LoginRequest;
import com.atmaram.ems.dto.request.RegisterRequest;
import com.atmaram.ems.dto.response.LoginResponse;
import com.atmaram.ems.dto.response.RegisterResponse;
import com.atmaram.ems.entity.Role;
import com.atmaram.ems.entity.User;
import com.atmaram.ems.enums.RoleType;
import com.atmaram.ems.repository.RoleRepository;
import com.atmaram.ems.repository.UserRepository;
import com.atmaram.ems.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.atmaram.ems.security.CustomUserDetails;
import com.atmaram.ems.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {

    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
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
    @Override
    public LoginResponse login(LoginRequest request) {

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
    );

    User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    String token = jwtService.generateToken(
            new CustomUserDetails(user)
    );

    return new LoginResponse(
            token,
            user.getUsername(),
            user.getRole().getName().name(),
            "Login Successful"
    );
}
    
}