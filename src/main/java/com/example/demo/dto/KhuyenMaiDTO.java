package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class KhuyenMaiDTO {
    private String ma;
    private String moTa;
    private String loaiKhuyenMai;
    private Float phanTramGiamGia;
    private BigDecimal soTienGiam;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String dieuKien;
    private List<String> maSanPhams;
}
