package com.example.demo.repository;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Long> {
    Optional<KhuyenMai> findByMa(String ma);

}
