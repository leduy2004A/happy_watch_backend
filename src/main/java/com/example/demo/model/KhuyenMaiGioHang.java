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
@Table(name = "khuyen_mai_gio_hang")
public class KhuyenMaiGioHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ma;

    @Column
    private String ten;

    @Column(name = "Loai_khuyen_mai")
    private String loaiKhuyenMai;

    @Column(name = "Phan_tram_giam_gia")
    private Float phanTramGiamGia;

    @Column(name = "So_tien_giam")
    private Double soTienGiam;

    @Column(name = "Ngay_bat_dau")
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;

    @Column(name = "Ngay_ket_thuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;

    @Column(name = "So_luong")
    private Integer soLuong;

    @Column(name = "Dieu_kien")
    private String dieuKien;

    @ManyToOne
    @JoinColumn(name = "id_gio_hang")
    private GioHang gioHang;

    // Getters and Setters
}

