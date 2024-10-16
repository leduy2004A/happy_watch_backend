package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class ChiTietHoaDonDTO {
    private Long chiTietHoaDonId;
    private Long hoaDonId;
    private BigDecimal tongTienChiTietHoaDon;
    private Long sanPhamId;
    private String maSanPham;
    private String tenSanPham;
    private BigDecimal giaSanPham;
    private Long khuyenMaiId;
    private Long chiTietSanPhamId;
    private String maSanPhamChiTiet;
    private String chatLieuDaySanPham;
    private String chatLieuVoSanPham;
    private String hinhDangSanPham;
    private String loaiKinhSanPham;
    private String loaiMaySanPham;
    private String mauSacSanPham;
    private Integer soLuong;
    private BigDecimal giaTungSanPham;

    // Trường danh sách hình ảnh
    private List<String> hinhAnh;

    // Constructor phù hợp với JPQL query (không bao gồm hinhAnh)
    public ChiTietHoaDonDTO(
            Long chiTietHoaDonId,
            Long hoaDonId,
            BigDecimal tongTienChiTietHoaDon,
            Long sanPhamId,
            String maSanPham,
            String tenSanPham,
            BigDecimal giaSanPham,
            Long khuyenMaiId,
            Long chiTietSanPhamId,
            String maSanPhamChiTiet,
            String chatLieuDaySanPham,
            String chatLieuVoSanPham,
            String hinhDangSanPham,
            String loaiKinhSanPham,
            String loaiMaySanPham,
            String mauSacSanPham,
            Integer soLuong,
            BigDecimal giaTungSanPham) {
        this.chiTietHoaDonId = chiTietHoaDonId;
        this.hoaDonId = hoaDonId;
        this.tongTienChiTietHoaDon = tongTienChiTietHoaDon;
        this.sanPhamId = sanPhamId;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.khuyenMaiId = khuyenMaiId;
        this.chiTietSanPhamId = chiTietSanPhamId;
        this.maSanPhamChiTiet = maSanPhamChiTiet;
        this.chatLieuDaySanPham = chatLieuDaySanPham;
        this.chatLieuVoSanPham = chatLieuVoSanPham;
        this.hinhDangSanPham = hinhDangSanPham;
        this.loaiKinhSanPham = loaiKinhSanPham;
        this.loaiMaySanPham = loaiMaySanPham;
        this.mauSacSanPham = mauSacSanPham;
        this.soLuong = soLuong;
        this.giaTungSanPham = giaTungSanPham;
    }
}
