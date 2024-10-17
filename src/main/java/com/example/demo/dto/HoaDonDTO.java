package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {

    private Long hoaDonId;
    private String hoaDonMa;
    private String tenNguoiNhan;
    private BigDecimal gia;
    private String diaChiGiaoHang;
    private Date ngayTao;
    private String trangThai;

    private Long nguoiDungId;
    private String nguoiDungMa;
    private String nguoiDungTen;
    private String nguoiDungUsername;
    private String nguoiDungDienThoai;

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

    // Constructors, getters, and setters
}

