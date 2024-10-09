package com.example.demo.controller;

import com.example.demo.model.ChatLieuDay;
import com.example.demo.model.ChatLieuVo;
import com.example.demo.service.ChatLieuVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
