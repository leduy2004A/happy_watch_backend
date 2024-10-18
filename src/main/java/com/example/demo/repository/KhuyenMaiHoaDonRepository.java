package com.example.demo.repository;

import com.example.demo.model.KhuyenMaiHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhuyenMaiHoaDonRepository extends JpaRepository<KhuyenMaiHoaDon,Integer> {
}
