package com.example.demo.repository;

import com.example.demo.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    Page<SanPham> findAll(Pageable pageable);
}
