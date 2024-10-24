package com.example.demo.dto;

import com.example.demo.model.HinhAnh;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamChiTietDTO {
    private Long sanPhamId;
    private Long chiTietSanPhamId;
    private String maSanPham;
    private List<String> hinhAnh;
    private String tenSanPham;
    private BigDecimal gia;
    private BigDecimal giaSauGiam;
    private String tenMauSac;
    private String loaiMay;
    private String chatLieuVo;
    private String chatLieuDay;
    private String kichThuoc;
    private String chongNuoc;
    private String loaiKinh;
    private String xuatXu;
    private String baoHanh;
    private String hinhDang;
    private String gioiTinh;
    private Integer canNang;
    private Integer soLuong;
}
