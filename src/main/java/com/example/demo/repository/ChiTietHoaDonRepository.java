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
        SELECT new com.example.demo.dto.ChiTietHoaDonDTO(
            cthd.id, 
            hd.id, 
            hd.gia, 
            sp.id, 
            sp.ma, 
            sp.ten, 
            sp.gia, 
            sp.khuyenMai.id, 
            ctsp.id, 
            ctsp.ma, 
            ctsp.chatLieuDay.ten, 
            ctsp.chatLieuVo.ten, 
            ctsp.hinhDang.ten, 
            ctsp.loaiKinh.ten, 
            ctsp.loaiMay.ten, 
            ctsp.mauSac.ten, 
            cthd.soLuong, 
            cthd.giaTungSanPham
        ) 
        FROM ChiTietHoaDon cthd
        JOIN cthd.chiTietSanPham ctsp
        JOIN ctsp.sanPham sp
        JOIN cthd.hoaDon hd
    """)
    Page<ChiTietHoaDonDTO> findAllChiTietHoaDon(Pageable pageable);

    @Query("""
    SELECT new com.example.demo.dto.ChiTietHoaDonDTO(cthd.id, hd.id, hd.gia, sp.id, sp.ma, sp.ten, sp.gia, sp.khuyenMai.id, ctsp.id, ctsp.ma, ctsp.chatLieuDay.ten, ctsp.chatLieuVo.ten, ctsp.hinhDang.ten, ctsp.loaiKinh.ten, ctsp.loaiMay.ten, ctsp.mauSac.ten, cthd.soLuong, cthd.giaTungSanPham) 
    FROM ChiTietHoaDon cthd
    JOIN ChiTietSanPham ctsp ON cthd.chiTietSanPham.id = ctsp.id
    JOIN SanPham sp ON ctsp.sanPham.id = sp.id
    JOIN HoaDon hd ON cthd.hoaDon.id = hd.id
    WHERE cthd.id = :id
""")
    Optional<ChiTietHoaDonDTO> findChiTietHoaDonDTOById(@Param("id") Long id);

    @Query(""" 
    SELECT new com.example.demo.dto.ChiTietHoaDonDTO(cthd.id, hd.id, hd.gia, sp.id, sp.ma, sp.ten, sp.gia, sp.khuyenMai.id, ctsp.id, ctsp.ma, ctsp.chatLieuDay.ten, ctsp.chatLieuVo.ten, ctsp.hinhDang.ten, ctsp.loaiKinh.ten, ctsp.loaiMay.ten, ctsp.mauSac.ten, cthd.soLuong, cthd.giaTungSanPham) 
    FROM ChiTietHoaDon cthd
    JOIN ChiTietSanPham ctsp ON cthd.chiTietSanPham.id = ctsp.id
    JOIN SanPham sp ON ctsp.sanPham.id = sp.id
    JOIN HoaDon hd ON cthd.hoaDon.id = hd.id
    WHERE hd.id = :idHD
""")
    List<ChiTietHoaDonDTO> findAllChiTietHoaDonDTOByIdHoaDon(@Param("idHD") Long idHD);

    List<ChiTietHoaDon> findByHoaDonId(Long hoaDonId);
    Page<ChiTietHoaDon> findAll(Pageable pageable);

    @Query("SELECT cthd FROM ChiTietHoaDon cthd WHERE cthd.id = :chiTietHoaDonId")
    Optional<ChiTietHoaDon> findChiTietHoaDonById(@Param("chiTietHoaDonId") Long chiTietHoaDonId);
}
