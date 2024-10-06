package com.example.demo.model;
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

    @Column
    private String kichThuoc;

    @Column
    private String chongNuoc;

    @ManyToOne
    @JoinColumn(name = "id_loai_kinh")
    private LoaiKinh loaiKinh;

    @Column
    private String hinhDang;

    @Column
    private String xuatXu;

    @Column
    private String baoHanh;

    // Getters and Setters
}
