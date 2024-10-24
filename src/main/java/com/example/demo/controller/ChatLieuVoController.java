package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ChatLieuVo;
import com.example.demo.service.ChatLieuVoService;

@RestController
@RequestMapping("/api/chatlieuvo")
public class ChatLieuVoController {

    @Autowired
    private ChatLieuVoService chatLieuVoService;

    @GetMapping("/all")
    public ResponseEntity<Page<ChatLieuVo>> getAllChatLieuVoWithDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieuVo> chatLieuVoList = chatLieuVoService.getAllChatLieuVoWithDetails(pageable);
        return ResponseEntity.ok(chatLieuVoList);
    }

    @GetMapping("/chi-tiet")
    public ResponseEntity<ChatLieuVo> getChatLieuVoByMaChiTiet(@RequestParam String maChiTiet) {
        Optional<ChatLieuVo> chatLieuVoOptional = chatLieuVoService.getChatLieuVoByMaChiTiet(maChiTiet);
        return chatLieuVoOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ChatLieuVo> addChatLieuVo(@RequestBody ChatLieuVo chatLieuVo) {
        Optional<ChatLieuVo> chatLieuVoOptional = chatLieuVoService.addChatLieuVo(chatLieuVo);
        return chatLieuVoOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ChatLieuVo> updateChatLieuVo(@RequestBody ChatLieuVo chatLieuVo) {
        Optional<ChatLieuVo> chatLieuVoOptional = chatLieuVoService.updateChatLieuVo(chatLieuVo);
        return chatLieuVoOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
