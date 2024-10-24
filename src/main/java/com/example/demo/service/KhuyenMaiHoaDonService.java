package com.example.demo.service;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiHoaDon;
import com.example.demo.repository.KhuyenMaiHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhuyenMaiHoaDonService {
    @Autowired
    private KhuyenMaiHoaDonRepository khuyenMaiHoaDonRepository;
    public KhuyenMaiHoaDon addKhuyenMai(KhuyenMaiHoaDon khuyenMai) {
        return khuyenMaiHoaDonRepository.save(khuyenMai);
    }
}
