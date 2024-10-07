package com.example.demo.repository;

import com.example.demo.model.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {
    List<ChiTietSanPham> findBySanPham_Id(Long sanPhamId);
    Optional<ChiTietSanPham> findByMa(String ma);
}
