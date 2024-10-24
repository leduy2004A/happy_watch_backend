package com.example.demo.repository;

import com.example.demo.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    Page<SanPham> findAll(Pageable pageable);
    List<SanPham> findByMaIn(List<String> maSanPhams);
    SanPham findByMa(String ma);
    @Query("""
    select sp from SanPham sp where sp.ma = :ma
""")
    Optional<SanPham> findSanPhamByma(String ma);
}
