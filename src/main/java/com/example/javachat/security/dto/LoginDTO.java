package com.example.javachat.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginDTO {
    private String name;

    @Builder
    public LoginDTO(String name) {
        this.name = name;
    }
}