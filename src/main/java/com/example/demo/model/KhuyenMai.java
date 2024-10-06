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

    @Column
    private String moTa;

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
    private String dieuKien;

    // Getters and Setters
}
