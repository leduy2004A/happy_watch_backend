package com.example.demo.service;

import com.example.demo.model.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> getAllCustomers() {
        return nguoiDungRepository.findAllKhachHang();
    }


}
