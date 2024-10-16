package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ma;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "id_loai_may")
    private LoaiMay loaiMay;

    @ManyToOne
    @JoinColumn(name = "id_chat_lieu_vo")
    private ChatLieuVo chatLieuVo;

    @ManyToOne
    @JoinColumn(name = "id_chat_lieu_day")
    private ChatLieuDay chatLieuDay;

    @Column(name = "kich_thuoc")
    private String kichThuoc;

    @Column(name = "chong_nuoc")
    private String chongNuoc;

    @ManyToOne
    @JoinColumn(name = "id_loai_kinh")
    private LoaiKinh loaiKinh;

    @Column(name = "xuat_xu")
    private String xuatXu;

    @Column(name = "bao_hanh")
    private String baoHanh;
    @ManyToOne
    @JoinColumn(name = "id_hinh_dang")
    private HinhDang hinhDang;
    // Getters and Setters
}
