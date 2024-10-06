package com.example.demo.repository;

import com.example.demo.model.KhuyenMaiGioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuyenMaiGioHangRepository extends JpaRepository<KhuyenMaiGioHang, Long> {
}
