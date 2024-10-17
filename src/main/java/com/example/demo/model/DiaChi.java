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

    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")

    private NguoiDung nguoiDung;
}
