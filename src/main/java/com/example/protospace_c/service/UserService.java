package com.example.protospace_c.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.protospace_c.entity.UserEntity;         
import com.example.protospace_c.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
  
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUserWithEncryptedPassword(UserEntity userEntity){
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        userRepository.insert(userEntity);
    }
}