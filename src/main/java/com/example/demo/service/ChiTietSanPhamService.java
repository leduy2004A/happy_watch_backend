package com.example.demo.service;

import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.SanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChiTietSanPhamService {
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    public ChiTietSanPham addChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        Optional<SanPham> sanPhamOpt = sanPhamRepository.findSanPhamByma(chiTietSanPham.getSanPham().getMa());
        if (sanPhamOpt.isPresent()) {
            chiTietSanPham.setSanPham(sanPhamOpt.get());
            return chiTietSanPhamRepository.save(chiTietSanPham);
        } else {
            throw new RuntimeException("Sản phẩm không tồn tại với mã: " + chiTietSanPham.getSanPham().getMa());
        }
    }
    public ChiTietSanPham updateChiTietSanPham(String maChiTietSanPham, ChiTietSanPham chiTietSanPhamUpdate) {
        Optional<ChiTietSanPham> chiTietSanPhamOpt = chiTietSanPhamRepository.findByMa(maChiTietSanPham);

        if (chiTietSanPhamOpt.isPresent()) {
            ChiTietSanPham existingChiTietSanPham = chiTietSanPhamOpt.get();

            existingChiTietSanPham.setMa(chiTietSanPhamUpdate.getMa());
            existingChiTietSanPham.setMauSac(chiTietSanPhamUpdate.getMauSac());
            existingChiTietSanPham.setLoaiMay(chiTietSanPhamUpdate.getLoaiMay());
            existingChiTietSanPham.setGia(chiTietSanPhamUpdate.getGia());
            existingChiTietSanPham.setChatLieuVo(chiTietSanPhamUpdate.getChatLieuVo());
            existingChiTietSanPham.setChatLieuDay(chiTietSanPhamUpdate.getChatLieuDay());
            existingChiTietSanPham.setKichThuoc(chiTietSanPhamUpdate.getKichThuoc());
            existingChiTietSanPham.setChongNuoc(chiTietSanPhamUpdate.getChongNuoc());
            existingChiTietSanPham.setXuatXu(chiTietSanPhamUpdate.getXuatXu());
            existingChiTietSanPham.setBaoHanh(chiTietSanPhamUpdate.getBaoHanh());
            existingChiTietSanPham.setSoLuong(chiTietSanPhamUpdate.getSoLuong());
            return chiTietSanPhamRepository.save(existingChiTietSanPham);
        } else {
            throw new RuntimeException("Chi tiết sản phẩm không tồn tại với mã: " + maChiTietSanPham);
        }
    }
}
