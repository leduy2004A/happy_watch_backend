package com.example.demo.service;

import com.example.demo.model.KhuyenMai;
import com.example.demo.repository.KhuyenMaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    public KhuyenMai addKhuyenMai(KhuyenMai khuyenMai) {
        return khuyenMaiRepository.save(khuyenMai);
    }
    public List<KhuyenMai> getAllKhuyenMai() {
        return khuyenMaiRepository.findAll();
    }

    public Optional<KhuyenMai> getKhuyenMaiByMa(String ma) {
        return khuyenMaiRepository.findByMa(ma);
    }

    public KhuyenMai updateKhuyenMai(Long id, KhuyenMai khuyenMai) {
        Optional<KhuyenMai> existing = khuyenMaiRepository.findById(id);
        if (existing.isPresent()) {
            KhuyenMai updated = existing.get();
            updated.setMa(khuyenMai.getMa());
            updated.setMoTa(khuyenMai.getMoTa());
            updated.setLoaiKhuyenMai(khuyenMai.getLoaiKhuyenMai());
            updated.setPhanTramGiamGia(khuyenMai.getPhanTramGiamGia());
            updated.setSoTienGiam(khuyenMai.getSoTienGiam());
            updated.setNgayBatDau(khuyenMai.getNgayBatDau());
            updated.setNgayKetThuc(khuyenMai.getNgayKetThuc());
            updated.setDieuKien(khuyenMai.getDieuKien());
            return khuyenMaiRepository.save(updated);
        }
        return null;
    }

}
