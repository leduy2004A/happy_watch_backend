package com.example.demo.repository;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.model.ChiTietHoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {
    @Query("""
    SELECT new com.example.demo.dto.ChiTietHoaDonDTO(cthd.id, hd.id, hd.gia, sp.id, sp.ma, sp.ten, sp.gia, sp.khuyenMai.id, ctsp.id, ctsp.ma, cthd.soLuong) 
    FROM ChiTietHoaDon cthd
    join ChiTietSanPham ctsp on cthd.chiTietSanPham.id = ctsp.id
    join SanPham sp on ctsp.sanPham.id = sp.id
    join HoaDon hd on cthd.hoaDon.id = hd.id
""")
    Page<ChiTietHoaDonDTO> findAllChiTietHoaDon(Pageable pageable);

    @Query("""
    SELECT new com.example.demo.dto.ChiTietHoaDonDTO(cthd.id, hd.id, hd.gia, sp.id, sp.ma, sp.ten, sp.gia, sp.khuyenMai.id, ctsp.id, ctsp.ma, cthd.soLuong) 
    FROM ChiTietHoaDon cthd
    JOIN ChiTietSanPham ctsp ON cthd.chiTietSanPham.id = ctsp.id
    JOIN SanPham sp ON ctsp.sanPham.id = sp.id
    JOIN HoaDon hd ON cthd.hoaDon.id = hd.id
    WHERE cthd.id = :id
""")
    Optional<ChiTietHoaDonDTO> findChiTietHoaDonDTOById(@Param("id") Long id);

    Page<ChiTietHoaDon> findAll(Pageable pageable);

    @Query("SELECT cthd FROM ChiTietHoaDon cthd WHERE cthd.id = :chiTietHoaDonId")
    Optional<ChiTietHoaDon> findChiTietHoaDonById(@Param("chiTietHoaDonId") Long chiTietHoaDonId);
}
