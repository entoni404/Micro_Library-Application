package com.restsecurity.authservice.manager;

import com.restsecurity.authservice.model.RoleEntity;
import com.restsecurity.authservice.model.RoleEnum;
import com.restsecurity.authservice.model.UserEntity;
import com.restsecurity.authservice.repository.RoleRepository;
import com.restsecurity.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserManager {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    
    public Optional<UserEntity> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    private boolean isValidUsername(String userName) {
        return userRepository.existsByUsername(userName);
    }
    
    public void createUser(UserEntity user) {
        if (isValidUsername(user.getUsername())) {
            throw new IllegalStateException("The username already exists.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Ths email address is incorrect or is already in use.");
        }
        userRepository.save(user);
    }
    
    public RoleEntity getRoleByName(RoleEnum name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Could not find role: " + name.name()));
    }
}
