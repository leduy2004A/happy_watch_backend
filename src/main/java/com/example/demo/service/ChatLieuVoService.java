package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChatLieuVo;
import com.example.demo.repository.ChatLieuVoRepository;

@Service
public class ChatLieuVoService {

    @Autowired
    private ChatLieuVoRepository chatLieuVoRepository;

    public Page<ChatLieuVo> getAllChatLieuVoWithDetails(Pageable pageable) {

        Page<ChatLieuVo> chatLieuVos = chatLieuVoRepository.findAll(pageable);
        List<ChatLieuVo> result = new ArrayList<>();

        for (ChatLieuVo chatLieuVo : chatLieuVos.getContent()) {
            List<ChatLieuVo> chatLieuVos1 = chatLieuVoRepository.findChatLieuVoBySanPhamId(chatLieuVo.getId());
            for (ChatLieuVo chatLieuVo1 : chatLieuVos1) {
                ChatLieuVo dto = new ChatLieuVo();
                dto.setId(chatLieuVo1.getId());
                dto.setMa(chatLieuVo1.getMa());
                dto.setTen(chatLieuVo1.getTen());

                result.add(dto);
            }
        }
        return new PageImpl<>(result, pageable, chatLieuVos.getTotalElements());
    }
    public Optional<ChatLieuVo> getChatLieuVoByMaChiTiet(String maChiTiet) {
        Optional<ChatLieuVo> chatLieuVoOptional = chatLieuVoRepository.findByMa(maChiTiet);
        if (chatLieuVoOptional.isPresent()) {
            ChatLieuVo chatLieuVo = chatLieuVoOptional.get();
            ChatLieuVo dto = new ChatLieuVo();
            dto.setId(chatLieuVo.getId());
            dto.setMa(chatLieuVo.getMa());
            dto.setTen(chatLieuVo.getTen());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<ChatLieuVo> addChatLieuVo(ChatLieuVo chatLieuVo) {
        ChatLieuVo dto = new ChatLieuVo();
        dto.setId(chatLieuVo.getId());
        dto.setMa(chatLieuVo.getMa());
        dto.setTen(chatLieuVo.getTen());
        chatLieuVoRepository.save(dto);
        return Optional.of(dto);
    }

    public Optional<ChatLieuVo> updateChatLieuVo(ChatLieuVo chatLieuVo) {
        Optional<ChatLieuVo> chatLieuVoOptional = chatLieuVoRepository.findByMa(chatLieuVo.getMa());

        if (chatLieuVoOptional.isPresent()) {
            ChatLieuVo existingChatLieuVo = chatLieuVoOptional.get();
            existingChatLieuVo.setTen(chatLieuVo.getTen()); // Cập nhật thuộc tính

            // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
            chatLieuVoRepository.save(existingChatLieuVo);

            return Optional.of(existingChatLieuVo);
        }

        return Optional.empty();
    }
}
