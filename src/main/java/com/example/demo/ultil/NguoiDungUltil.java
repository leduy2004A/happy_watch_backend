package com.example.demo.ultil;

import com.example.demo.model.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NguoiDungUltil {
    @Autowired
    private NguoiDungRepository ndr;
    @Autowired
    private hashPassword passwordHash;
    public boolean checkTrungMatKhau(String password, String confirm_password){
        confirm_password = passwordHash.passwordEncoder().encode(confirm_password);
      return passwordHash.passwordEncoder().matches(password,confirm_password);

    }
    public boolean checkNguoiDungDaDangKy(String username)
    {
        List<NguoiDung> lstNguoidungtontai =  ndr.findNguoiDungByUsername(username);
        if(lstNguoidungtontai.size() !=0){
            return false;
        }
        return true;
    }
}
