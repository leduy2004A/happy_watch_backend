package com.example.demo.repository;

import com.example.demo.model.ChatLieuDay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatLieuDayRepository extends JpaRepository<ChatLieuDay, Long> {
    Page<ChatLieuDay> findAll(Pageable pageable);

    @Query("SELECT c FROM ChatLieuDay c WHERE c.id = :chatLieuDayId")
    List<ChatLieuDay> findChatLieuDayBySanPhamId(@Param("chatLieuDayId") Long chatLieuDayId);

    Optional<ChatLieuDay> findByMa(String ma);
}