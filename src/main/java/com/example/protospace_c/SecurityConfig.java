package com.example.protospace_c;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // 静的ファイルやトップページ、ログイン・新規登録画面は誰でもアクセス可能にする
                        .requestMatchers("/css/**", "/images/**", "/js/**", "/", "/users/sign_up", "/users/login").permitAll()
                        // 2. 新規登録のPOST送信先URL（コントローラーの@PostMappingに合わせる。例として /users/sign_up と仮定）
                        .requestMatchers(HttpMethod.POST, "/users/sign_up").permitAll() 
                        .anyRequest().authenticated())
                
                .formLogin(login -> login
                        .loginProcessingUrl("/login")     // ログイン処理を実行するURL
                        .loginPage("/users/login")        // ログイン画面のURL
                        .defaultSuccessUrl("/", true)     // ログイン成功時の遷移先（トップページ）
                        .failureUrl("/users/login?error") // ログイン失敗時の遷移先
                        .usernameParameter("email")       // ユーザー名として使うフォームのname属性（UserFormのemailと一致）
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")             // ログアウト処理を実行するURL
                        .logoutSuccessUrl("/"));          // ログアウト成功時の遷移先

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}