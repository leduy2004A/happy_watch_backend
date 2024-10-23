package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NguoiDungDTO {
    private Long id;
    private String ma;
    private String avatar;
    private String ten;
    private String username;
    private String email;
    private String gioiTinh;
    private boolean trangThai;
    private String dienThoai;



}
