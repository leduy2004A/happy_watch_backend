package com.example.demo.repository;

import com.example.demo.model.ChatLieuVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatLieuVoRepository extends JpaRepository<ChatLieuVo, Long> {
    Page<ChatLieuVo> findAll(Pageable pageable);

    @Query("SELECT c FROM ChatLieuVo c WHERE c.id = :chatLieuVoId")
    List<ChatLieuVo> findChatLieuVoBySanPhamId(@Param("chatLieuVoId") Long chatLieuVoId);

    Optional<ChatLieuVo> findByMa(String ma);
}
