package com.restsecurity.authservice.service;

import com.restsecurity.authservice.manager.UserManager;
import com.restsecurity.authservice.model.UserEntity;
import com.restsecurity.authservice.payload.request.LoginRequest;
import com.restsecurity.authservice.payload.request.SignupRequest;
import com.restsecurity.authservice.payload.response.JwtResponse;
import com.restsecurity.authservice.security.jwt.JwtUtils;
import com.restsecurity.authservice.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserManager userManager;
    private final JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private final PasswordEncoder encoder;
    
    
    public boolean validateJwtToken(String token) {
        return jwtUtils.validateJwtToken(token);
    }
    public JwtResponse authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
         
    }
    public LoginRequest registerUser(SignupRequest signUpRequest) {
        UserEntity user = mapSignUpRequest(signUpRequest);
        userManager.createUser(user);
        return new LoginRequest(signUpRequest.getUsername(), signUpRequest.getPassword());
    }
    
    private UserEntity mapSignUpRequest(SignupRequest signUpRequest) {
        UserEntity user = new UserEntity();
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getUsername());
        user.setRole(userManager.getRoleByName(signUpRequest.getRole()));
        return user;
    }
    
    
}
