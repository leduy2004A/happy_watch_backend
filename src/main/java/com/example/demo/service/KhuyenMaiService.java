package com.example.demo.service;

import com.example.demo.dto.KhuyenMaiDTO;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.SanPham;
import com.example.demo.repository.KhuyenMaiRepository;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhuyenMaiService {

    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public KhuyenMai addKhuyenMai(KhuyenMai khuyenMai) {
        return khuyenMaiRepository.save(khuyenMai);
    }
    public List<KhuyenMai> getAllKhuyenMai() {
        return khuyenMaiRepository.findAll();
    }

    public Optional<KhuyenMai> getKhuyenMaiByMa(String ma) {
        return khuyenMaiRepository.findByMa(ma);
    }

    public KhuyenMai updateKhuyenMaiByMa(String ma, KhuyenMai khuyenMaiDetails) {
        Optional<KhuyenMai> existingKhuyenMaiOptional = khuyenMaiRepository.findByMa(ma);
        if (existingKhuyenMaiOptional.isPresent()) {
            KhuyenMai existingKhuyenMai = existingKhuyenMaiOptional.get();
            existingKhuyenMai.setMoTa(khuyenMaiDetails.getMoTa());
            existingKhuyenMai.setLoaiKhuyenMai(khuyenMaiDetails.getLoaiKhuyenMai());
            existingKhuyenMai.setPhanTramGiamGia(khuyenMaiDetails.getPhanTramGiamGia());
            existingKhuyenMai.setSoTienGiam(khuyenMaiDetails.getSoTienGiam());
            existingKhuyenMai.setNgayBatDau(khuyenMaiDetails.getNgayBatDau());
            existingKhuyenMai.setNgayKetThuc(khuyenMaiDetails.getNgayKetThuc());
            existingKhuyenMai.setDieuKien(khuyenMaiDetails.getDieuKien());

            return khuyenMaiRepository.save(existingKhuyenMai); // Lưu lại thông tin cập nhật
        } else {
            return null; // Nếu không tìm thấy, trả về null
        }
    }

    public KhuyenMai createKhuyenMai(KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setMa(khuyenMaiDTO.getMa());
        khuyenMai.setMoTa(khuyenMaiDTO.getMoTa());
        khuyenMai.setLoaiKhuyenMai(khuyenMaiDTO.getLoaiKhuyenMai());
        khuyenMai.setPhanTramGiamGia(khuyenMaiDTO.getPhanTramGiamGia());
        khuyenMai.setSoTienGiam(khuyenMaiDTO.getSoTienGiam());
        khuyenMai.setNgayBatDau(khuyenMaiDTO.getNgayBatDau());
        khuyenMai.setNgayKetThuc(khuyenMaiDTO.getNgayKetThuc());
        khuyenMai.setDieuKien(khuyenMaiDTO.getDieuKien());
        KhuyenMai savedKhuyenMai = khuyenMaiRepository.save(khuyenMai);
        List<SanPham> sanPhams = sanPhamRepository.findByMaIn(khuyenMaiDTO.getMaSanPhams());

        // Gán khuyến mãi cho các sản phẩm này
        for (SanPham sanPham : sanPhams) {
            sanPham.setKhuyenMai(savedKhuyenMai);
        }
        sanPhamRepository.saveAll(sanPhams);

        return savedKhuyenMai;
    }
}
