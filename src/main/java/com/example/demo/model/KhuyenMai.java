package com.example.demo.model;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private BigDecimal soTienGiam;

    @Column(name = "Ngay_bat_dau")
    @Temporal(TemporalType.DATE)
    private LocalDate ngayBatDau;

    @Column(name = "Ngay_ket_thuc")
    @Temporal(TemporalType.DATE)
    private LocalDate ngayKetThuc;

    @Column(name = "Dieu_kien")
    private String dieuKien;

    // Getters and Setters
}
