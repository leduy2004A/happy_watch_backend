package com.example.demo.service;

import com.example.demo.dto.SanPhamChiTietDTO;
import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.HinhAnh;
import com.example.demo.model.SanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    public Page<SanPhamChiTietDTO> getAllSanPhamWithDetails(Pageable pageable) {
        Page<SanPham> sanPhams = sanPhamRepository.findAll(pageable);
        List<SanPhamChiTietDTO> result = new ArrayList<>();
        for (SanPham sanPham : sanPhams.getContent()) {
            List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamRepository.findBySanPham_Id(sanPham.getId());
            for (ChiTietSanPham chiTiet : chiTietSanPhams) {
                SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
                List<String> hinhAnhs = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());
                dto.setSanPhamId(sanPham.getId());
                dto.setMaSanPham(sanPham.getMa());
                dto.setHinhAnh(hinhAnhs);
                dto.setTenSanPham(sanPham.getTen());
                dto.setGia(sanPham.getGia());
                dto.setTenMauSac(chiTiet.getMauSac().getTen());
                dto.setLoaiMay(chiTiet.getLoaiMay().getTen());
                dto.setChatLieuVo(chiTiet.getChatLieuVo().getTen());
                dto.setChatLieuDay(chiTiet.getChatLieuDay().getTen());
                dto.setKichThuoc(chiTiet.getKichThuoc());
                dto.setChongNuoc(chiTiet.getChongNuoc());
                dto.setLoaiKinh(chiTiet.getLoaiKinh().getTen());
                dto.setHinhDang(chiTiet.getHinhDang().getTen());
                dto.setGioiTinh(sanPham.getGioiTinh().getTen());
                dto.setXuatXu(chiTiet.getXuatXu());
                dto.setBaoHanh(chiTiet.getBaoHanh());
                result.add(dto);
            }
        }
        return new PageImpl<>(result, pageable, sanPhams.getTotalElements());
    }
    public Optional<SanPhamChiTietDTO> getSanPhamByMaChiTiet(String maChiTiet) {
        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findByMa(maChiTiet);
        if (chiTietSanPhamOptional.isPresent()) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamOptional.get();
            SanPham sanPham = chiTietSanPham.getSanPham();
            List<String> hinhAnhs = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());
            System.out.println(hinhAnhs);
            SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
            dto.setSanPhamId(sanPham.getId());
            dto.setMaSanPham(sanPham.getMa());
            dto.setHinhAnh(hinhAnhs);
            dto.setTenSanPham(sanPham.getTen());
            dto.setGia(sanPham.getGia());
            dto.setTenMauSac(chiTietSanPham.getMauSac().getTen());
            dto.setLoaiMay(chiTietSanPham.getLoaiMay().getTen());
            dto.setChatLieuVo(chiTietSanPham.getChatLieuVo().getTen());
            dto.setChatLieuDay(chiTietSanPham.getChatLieuDay().getTen());
            dto.setKichThuoc(chiTietSanPham.getKichThuoc());
            dto.setChongNuoc(chiTietSanPham.getChongNuoc());
            dto.setLoaiKinh(chiTietSanPham.getLoaiKinh().getTen());
            dto.setHinhDang(chiTietSanPham.getHinhDang().getTen());
            dto.setGioiTinh(sanPham.getGioiTinh().getTen());
            dto.setXuatXu(chiTietSanPham.getXuatXu());
            dto.setBaoHanh(chiTietSanPham.getBaoHanh());
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
