package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dia_chi")
public class DiaChi {
    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma", nullable = false)
    private String ma;

    @Column(name = "tinh_thanh_pho")
    private String tinhThanhPho;

    @Column(name = "quan_huyen")
    private String quanHuyen;

    @Column(name = "xa_phuong_thi_tran")
    private String xaPhuongThiTran;

    @Column(name = "dia_chi_cu_the")
    private String diaChiCuThe;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")

    private NguoiDung nguoiDung;
}
