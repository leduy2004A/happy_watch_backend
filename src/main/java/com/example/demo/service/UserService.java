package com.example.demo.service;

import com.example.demo.appException.AppException;
import com.example.demo.configure.WebSecurityConfig;
import com.example.demo.dto.NguoiDungRegisterDTO;
import com.example.demo.dto.tokenDTO;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import com.example.demo.ultil.NguoiDungUltil;
import com.example.demo.ultil.hashPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private NguoiDungRepository ndr;
    @Autowired
    private NguoiDungUltil ndu;
    @Autowired
    private JwtTokenProvider jwt;
    @Autowired
    private hashPassword hashPassword;

    public tokenDTO registerUser(Map<String, Object> nguoiDungData) {
        NguoiDungRegisterDTO nguoiDungRegisterDTO = new NguoiDungRegisterDTO(
                (String) nguoiDungData.get("ten"),
                (String) nguoiDungData.get("username"),
                (String) nguoiDungData.get("password")
        );
        if (Arrays.asList((String) nguoiDungData.get("ten"),
                (String) nguoiDungData.get("username"),
                (String) nguoiDungData.get("password")).stream().anyMatch(item -> item == null)) {
            throw new AppException(400, "Thông tin người dùng không hợp lệ");
        }
        if (nguoiDungRegisterDTO != null) {
            if (ndu.checkTrungMatKhau(nguoiDungRegisterDTO.getPassword(), (String) nguoiDungData.get("confirm_password"))) {
                if (ndu.checkNguoiDungDaDangKy(nguoiDungRegisterDTO.getUsername())) {
                    NguoiDung nd = new NguoiDung(nguoiDungRegisterDTO);
                    nd.setPassword(hashPassword.passwordEncoder().encode(nd.getPassword()));
                    NguoiDung nguoiDungDangKi = ndr.save(nd);
                    String access_token = jwt.generateToken(new CustomUserDetail(nguoiDungDangKi));
                    return new tokenDTO(access_token);
                } else {
                    throw new AppException(409, "Người dùng đã tồn tại");
                }
            } else {
                throw new AppException(401, "Lỗi mật khẩu");
            }
        }

        throw new AppException(400, "Thông tin người dùng không hợp lệ");
    }

}
