package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietDTO {
    private Long id;
    private Long idSanPhamChiTiet;
    private Long idGioHang;
    private Integer soLuong;
    private BigDecimal giaTungSanPham;
    private String tenSanPham;
    private String maSanPham;
    private String maGioHang;
}
