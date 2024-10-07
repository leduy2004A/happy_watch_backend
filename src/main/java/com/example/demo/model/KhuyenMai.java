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
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ma;

    @Column(name = "Mo_ta")
    private String moTa;

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

    @Column(name = "Dieu_kien")
    private String dieuKien;

    // Getters and Setters
}
