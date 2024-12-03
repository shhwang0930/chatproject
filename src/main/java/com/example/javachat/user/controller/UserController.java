package com.example.javachat.user.controller;


import com.example.javachat.user.model.dto.JoinDTO;
import com.example.javachat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody JoinDTO joinDTO) {
        return userService.joinUser(joinDTO);
    }
}
