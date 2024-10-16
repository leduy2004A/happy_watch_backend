package com.example.demo.repository;

import com.example.demo.model.ChiTietGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.Optional;
@Repository
public interface ChiTietGioHangRepository extends JpaRepository<ChiTietGioHang, Long> {
    Set<ChiTietGioHang> findByGioHangId(Long gioHangId);
}
