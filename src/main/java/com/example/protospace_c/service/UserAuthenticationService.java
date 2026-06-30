package com.example.protospace_c.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.protospace_c.custom_user.CustomUserDetails; // 2. 前のステップで作ったクラスの住所
import com.example.protospace_c.entity.UserEntity;         // 2. Entityの住所
import com.example.protospace_c.repository.UserRepository; // 2. Repositoryの住所
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAuthenticationService implements UserDetailsService { // 自作じゃないほうのインタフェースですね！
  
    private final UserRepository userRepository;

    @Override // ログインするときのメアドの存在確認
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null){
            // 3. バグ修正: 変数のemailが入るようにダブルクォーテーションを外しました
            throw new UsernameNotFoundException("User not found with email: " + email); 
        }
        // 4. クラス名を複数形の CustomUserDetails に合わせました
        return new CustomUserDetails(userEntity); 
    }
}