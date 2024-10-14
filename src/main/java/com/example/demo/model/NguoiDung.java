package com.example.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nguoi_dung")
public class NguoiDung {
    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Dia_chi")
    private String diaChi;

    @Column(name = "Dien_thoai")
    private String dienThoai;

    @ManyToOne
    @JoinColumn(name = "Id_vai_tro")
    private VaiTro vaiTro;
}

