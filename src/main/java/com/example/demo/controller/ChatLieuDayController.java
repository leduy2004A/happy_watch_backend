package com.example.demo.controller;

import com.example.demo.model.ChatLieuDay;
import com.example.demo.service.ChatLieuDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/chatlieuday")
public class ChatLieuDayController {

    @Autowired
    private ChatLieuDayService chatLieuDayService;

    @GetMapping("/all")
    public ResponseEntity<Page<ChatLieuDay>> getAllChatLieuDayWithDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChatLieuDay> chatLieuDayList = chatLieuDayService.getAllChatLieuDayWithDetails(pageable);
        return ResponseEntity.ok(chatLieuDayList);
    }

    @GetMapping("/chi-tiet")
    public ResponseEntity<ChatLieuDay> getChatLieuDayByMaChiTiet(@RequestParam String maChiTiet) {
        Optional<ChatLieuDay> chatLieuDayOptional = chatLieuDayService.getChatLieuDayByMaChiTiet(maChiTiet);
        return chatLieuDayOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ChatLieuDay> addChatLieuDay(@RequestBody ChatLieuDay chatLieuDay) {
        Optional<ChatLieuDay> chatLieuDayOptional = chatLieuDayService.addChatLieuDay(chatLieuDay);
        return chatLieuDayOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ChatLieuDay> updateChatLieuDay(@RequestBody ChatLieuDay chatLieuDay) {
        Optional<ChatLieuDay> chatLieuDayOptional = chatLieuDayService.updateChatLieuDay(chatLieuDay);
        return chatLieuDayOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
