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
    //CREATE TABLE dia_chi (
    //    Id INT PRIMARY KEY IDENTITY(1,1),
    //    Ma NVARCHAR(50) NOT NULL,
    //    dia_chi NVARCHAR(100) NOT NULL,
    //	Id_nguoi_dung INT FOREIGN KEY REFERENCES nguoi_dung(Id)
    //);

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Ten_nguoi_nhan")
    private String diaChi;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "Id_nguoi_dung")
    private NguoiDung nguoiDung;
}
