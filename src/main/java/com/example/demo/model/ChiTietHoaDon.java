package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chi_tiet_hoa_don")
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don")
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "Id_chi_tiet_san_pham")
    private ChiTietSanPham chiTietSanPham;

    @Column(name = "So_luong")
    private Integer soLuong;

    @Column(name = "can_nang")
    private Integer canNang;
    @Column(name = "Gia_tung_san_pham")
    private BigDecimal giaTungSanPham;
}
