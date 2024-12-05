package com.example.javachat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Web Security 설정
 */
@Slf4j
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/chat/join", "/chat/room","/chat/rooms").permitAll()
                                .requestMatchers("/auth/**").authenticated()
                                .requestMatchers("/chat/**").hasRole("USER")
                                .anyRequest().permitAll()  // 나머지 경로는 모두 허용
                )
                .formLogin((formLogin) ->
                        formLogin
                                .defaultSuccessUrl("/chat/room")
                );

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}