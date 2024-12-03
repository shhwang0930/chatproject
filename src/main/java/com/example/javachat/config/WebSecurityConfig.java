package com.example.javachat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Web Security 설정
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/auth/**").authenticated()
                                .requestMatchers("/chat/**").hasRole("USER")
                                .anyRequest().permitAll()  // 나머지 경로는 모두 허용
                )
                .formLogin(withDefaults());  // 기본 로그인 폼 활성화

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("test1")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("test2")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();

        UserDetails guest = User.builder()
                .username("guest")
                .password(passwordEncoder.encode("1234"))
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, guest);
    }
}