package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hoa_don")
public class HoaDon {
    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ma")
    private String ma;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "Id_nguoi_dung")
    private NguoiDung nguoiDung;

    @Column(name = "Ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "Gia")
    private Double gia;

    @Column(name = "Dia_chi_giao_hang")
    private String diaChiGiaoHang;

    @Column(name = "Ngay_tao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Column(name = "Trang_thai")
    private String trangThai;

    // Getters and Setters
}
