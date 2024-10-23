package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private BigDecimal gia;

    @Column(name = "tinh_thanh_pho")
    private String tinhThanhPho;

    @Column(name = "quan_huyen")
    private String quanHuyen;

    @Column(name = "xa_phuong_thi_tran")
    private String xaPhuongThiTran;

    @Column(name = "dia_chi_cu_the")
    private String diaChiCuThe;

    @Column(name = "Dien_thoai")
    private String dienThoai;

    @Column(name = "Ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "tong_can_nang")
    private Integer tongCanNang;

    @Column(name = "Trang_thai")
    private String trangThai;

    @Column(name = "loai_hoa_don")
    private String loaiHoaDon;
    @ManyToOne
    @JoinColumn(name = "id_thanh_toan")
    private ThanhToan thanhToan;

    @ManyToOne
    @JoinColumn(name = "id_nhan_vien")
    private NguoiDung nhanVien;
    @ManyToOne
    @JoinColumn(name = "id_khuyen_mai_hoa_don")
    private KhuyenMaiHoaDon khuyenMaiHoaDon;

    @OneToMany(mappedBy = "hoaDon")
    @JsonIgnore
    private List<ChiTietHoaDon> chiTietHoaDonList;
}