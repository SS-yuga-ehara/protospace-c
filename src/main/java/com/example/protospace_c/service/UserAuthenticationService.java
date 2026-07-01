package com.example.protospace_c.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.protospace_c.custom_user.CustomUserDetails;
import com.example.protospace_c.entity.UserEntity;         
import com.example.protospace_c.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAuthenticationService implements UserDetailsService {
  
    private final UserRepository userRepository;

    @Override // ログインするときのメアドの存在確認
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null){
            throw new UsernameNotFoundException("User not found with email: " + email); 
        }
        return new CustomUserDetails(userEntity); 
    }
}