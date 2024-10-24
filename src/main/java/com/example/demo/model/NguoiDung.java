package com.example.demo.model;
import com.example.demo.dto.NguoiDungRegisterDTO;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ma;

    @Column
    private String avatar;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Username")
    private String username;

    @Column(name = "dien_thoai")
    private String dienThoai;
    @Column(name = "Password")
    private String password;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "trang_thai")
    private boolean trangThai;

    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "id_vai_tro")
    private VaiTro vaiTro;

    public NguoiDung(NguoiDungRegisterDTO nguoiDungRegisterDTO) {
        this.avatar = "https://thuthuatnhanh.com/wp-content/uploads/2021/07/hinh-anh-hinh-nen-dong-ho.jpg";
        this.ten = nguoiDungRegisterDTO.getTen();
        this.username = nguoiDungRegisterDTO.getUsername();
        this.password = nguoiDungRegisterDTO.getPassword();
    }
}

