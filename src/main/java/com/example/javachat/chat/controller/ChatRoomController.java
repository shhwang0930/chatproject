package com.example.javachat.chat.controller;

import com.example.javachat.chat.model.dto.MessageDTO;
import com.example.javachat.chat.model.dto.RoomDTO;
import com.example.javachat.chat.repository.ChatRoomRepository;
import com.example.javachat.chat.service.ChatRoomService;
import com.example.javachat.chat.service.ChatService;
import com.example.javachat.security.JwtTokenProvider;
import com.example.javachat.security.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final JwtTokenProvider jwtTokenProvider;
    private final ChatRoomService chatRoomService;
    private final ChatService chatService;

    @GetMapping("/user")
    @ResponseBody
    public LoginDTO getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return LoginDTO.builder().name(name).token(jwtTokenProvider.generateToken(name)).build();
    }


    // 채팅 리스트 화면
    @GetMapping("/room")
    public String room(Model model) {
        return "/chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<RoomDTO> rooms() {
        return chatRoomService.getRooms();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public RoomDTO createRoom(@RequestParam String name) {
        return chatRoomService.createRoom(name);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public RoomDTO roomInfo(@PathVariable String roomId) {
        return chatRoomService.roomInfo(roomId);
    }

    @GetMapping("/msg/{roomId}")
    @ResponseBody
    public List<MessageDTO> getMessage(@PathVariable String roomId){
        return chatService.getMessage(roomId);
    }
}