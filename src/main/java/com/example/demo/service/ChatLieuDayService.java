package com.example.demo.service;

import com.example.demo.model.ChatLieuDay;
import com.example.demo.repository.ChatLieuDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuDayService {

    @Autowired
    private ChatLieuDayRepository chatLieuDayRepository;

    public Page<ChatLieuDay> getAllChatLieuDayWithDetails(Pageable pageable) {

        Page<ChatLieuDay> chatLieuDays = chatLieuDayRepository.findAll(pageable);
        List<ChatLieuDay> result = new ArrayList<>();

        for (ChatLieuDay chatLieuDay : chatLieuDays.getContent()) {
            List<ChatLieuDay> chatLieuDays1 = chatLieuDayRepository.findChatLieuDayBySanPhamId(chatLieuDay.getId());
            for (ChatLieuDay chatLieuDay1 : chatLieuDays1) {
                ChatLieuDay dto = new ChatLieuDay();
                dto.setId(chatLieuDay1.getId());
                dto.setMa(chatLieuDay1.getMa());
                dto.setTen(chatLieuDay1.getTen());

                result.add(dto);
            }
        }
        return new PageImpl<>(result, pageable, chatLieuDays.getTotalElements());
    }

    public Optional<ChatLieuDay> getChatLieuDayByMaChiTiet(String maChiTiet) {
        Optional<ChatLieuDay> chatLieuDayOptional = chatLieuDayRepository.findByMa(maChiTiet);
        if (chatLieuDayOptional.isPresent()) {
            ChatLieuDay chatLieuDay = chatLieuDayOptional.get();
            ChatLieuDay dto = new ChatLieuDay();
            dto.setId(chatLieuDay.getId());
            dto.setMa(chatLieuDay.getMa());
            dto.setTen(chatLieuDay.getTen());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<ChatLieuDay> addChatLieuDay(ChatLieuDay chatLieuDay) {
            ChatLieuDay dto = new ChatLieuDay();
            dto.setId(chatLieuDay.getId());
            dto.setMa(chatLieuDay.getMa());
            dto.setTen(chatLieuDay.getTen());
            chatLieuDayRepository.save(dto);
            return Optional.of(dto);
    }

    public Optional<ChatLieuDay> updateChatLieuDay(ChatLieuDay chatLieuDay) {
        Optional<ChatLieuDay> chatLieuDayOptional = chatLieuDayRepository.findByMa(chatLieuDay.getMa());

        if (chatLieuDayOptional.isPresent()) {
            ChatLieuDay existingChatLieuDay = chatLieuDayOptional.get();
            existingChatLieuDay.setTen(chatLieuDay.getTen()); // Cập nhật thuộc tính

            // Lưu đối tượng đã cập nhật vào cơ sở dữ liệu
            chatLieuDayRepository.save(existingChatLieuDay);

            return Optional.of(existingChatLieuDay);
        }

        return Optional.empty();
    }

}
