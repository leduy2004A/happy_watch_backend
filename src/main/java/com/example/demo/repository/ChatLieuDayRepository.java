package com.example.demo.repository;

import com.example.demo.model.ChatLieuDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuDayRepository extends JpaRepository<ChatLieuDay, Long> {
}
