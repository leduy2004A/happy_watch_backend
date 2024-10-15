package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonDTO {

    private Long hoaDonId;
    private String hoaDonMa;
    private String tenNguoiNhan;
    private Double gia;
    private String diaChiGiaoHang;
    private Date ngayTao;
    private String trangThai;

    private Long nguoiDungId;
    private String nguoiDungMa;
    private String nguoiDungTen;
    private String nguoiDungUsername;
    private String nguoiDungDiaChi;
    private String nguoiDungDienThoai;

    private Long vaiTroId;
    private String vaiTroTen;

    // Constructors, getters, and setters
}

