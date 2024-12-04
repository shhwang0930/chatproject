package com.example.javachat.user.controller;


import com.example.javachat.user.model.dto.JoinDTO;
import com.example.javachat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    @ResponseBody
    public String join(@RequestBody JoinDTO joinDTO) {
        System.out.println("Received username: " + joinDTO.getUsername());
        System.out.println("Received password: " + joinDTO.getPassword());
        return userService.joinUser(joinDTO);
    }

    @GetMapping("/chat/join")
    public String join(){
        return "/chat/join";
    }
}
