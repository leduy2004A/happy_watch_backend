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
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ma;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")
    private NguoiDung nguoiDung;

    @Column
    private String tenNguoiNhan;

    @Column
    private Double gia;

    @Column
    private String diaChiGiaoHang;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Column
    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "id_thanh_toan")
    private ThanhToan thanhToan;
}
