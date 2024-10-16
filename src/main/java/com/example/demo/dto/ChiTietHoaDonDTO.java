package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDonDTO {
    Long chiTietHoaDonId;

    Long hoaDonId;

    BigDecimal tongTienHoaDon;

    Long sanPhamId;

    String maSanPham;

    String tenSanPham;

    BigDecimal giaSanPham;

    Long khuyenMaiId;

    Long chiTietSanPhamId;

    String maSanPhamChiTiet;

    Integer soLuong;
}
