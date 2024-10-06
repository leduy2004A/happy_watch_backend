package com.example.demo.repository;

import com.example.demo.model.ChatLieuVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuVoRepository extends JpaRepository<ChatLieuVo, Long> {
}
