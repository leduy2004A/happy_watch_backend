package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {

    private Long hoaDonId;
    private String hoaDonMa;
    private String tenNguoiNhan;
    private BigDecimal gia;
    private String tinhThanhPho;
    private String quanHuyen;
    private String xaPhuongThiTran;
    private String diaChiCuThe;
    private String dienThoai;
    private LocalDate ngayTao;
    private String trangThai;
    private Long nguoiDungId;
    private String nguoiDungMa;
    private String nguoiDungTen;
    private String nguoiDungUsername;
    private Integer tongCanNang;
    private Long vaiTroId;
    private String vaiTroTen;
    private Long thanhToanId;
    private String maThanhToan;
    private String phuongThucThanhToan;
    private BigDecimal soTien;
    private Long nguoiTaoId;
    private String nguoiTaoMa;
    private String nguoiTaoTen;
    private String nguoiTaoUsername;
    private String loaiHoaDon;

}

