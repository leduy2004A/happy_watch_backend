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

    @Column
    private String loaiKhuyenMai;

    @Column
    private Float phanTramGiamGia;

    @Column
    private Double soTienGiam;

    @Column
    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;

    @Column
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;

    @Column
    private Integer soLuong;

    @Column
    private String dieuKien;

    @ManyToOne
    @JoinColumn(name = "id_gio_hang")
    private GioHang gioHang;

    // Getters and Setters
}

