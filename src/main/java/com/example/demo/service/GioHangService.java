package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.GioHangChiTietDTO;
import com.example.demo.model.ChiTietGioHang;
import com.example.demo.model.GioHang;
import com.example.demo.repository.ChiTietGioHangRepository;
import com.example.demo.repository.GioHangRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GioHangService {
    private final GioHangRepository gioHangRepository;
    private final ChiTietGioHangRepository chiTietGioHangRepository;

    public Page<GioHangChiTietDTO> getAllCartDetails(Pageable pageable) {
        Page<GioHang> gioHangs = gioHangRepository.findAll(pageable);
        List<GioHangChiTietDTO> result = new ArrayList<>();
        for (GioHang gioHang : gioHangs.getContent()) {
            Set<ChiTietGioHang> chiTietGioHangs = chiTietGioHangRepository.findByGioHangId(gioHang.getId());
            for (ChiTietGioHang chiTiet : chiTietGioHangs) {
                GioHangChiTietDTO dto = new GioHangChiTietDTO();
                dto.setId(chiTiet.getId());
                dto.setIdSanPhamChiTiet(chiTiet.getChiTietSanPham().getId());
                dto.setIdGioHang(gioHang.getId());
                dto.setSoLuong(chiTiet.getSoLuong());
                dto.setGiaTungSanPham(chiTiet.getGiaTungSanPham());
                dto.setTenSanPham(chiTiet.getChiTietSanPham().getSanPham().getTen());
                dto.setMaSanPham(chiTiet.getChiTietSanPham().getSanPham().getMa());
                dto.setMaGioHang(gioHang.getMa());
                result.add(dto);
            }
        }
        return new PageImpl<>(result, pageable, gioHangs.getTotalElements());
    }

    public Optional<GioHangChiTietDTO> getCartDetailById(Long id) {
        Optional<ChiTietGioHang> chiTietGioHang = chiTietGioHangRepository.findById(id);
        if (chiTietGioHang.isEmpty()) {
            return Optional.empty();
        }
        GioHangChiTietDTO dto = new GioHangChiTietDTO();
        dto.setId(chiTietGioHang.get().getId());
        dto.setIdSanPhamChiTiet(chiTietGioHang.get().getChiTietSanPham().getId());
        dto.setIdGioHang(chiTietGioHang.get().getGioHang().getId());
        dto.setSoLuong(chiTietGioHang.get().getSoLuong());
        dto.setGiaTungSanPham(chiTietGioHang.get().getGiaTungSanPham());
        dto.setTenSanPham(chiTietGioHang.get().getChiTietSanPham().getSanPham().getTen());
        dto.setMaSanPham(chiTietGioHang.get().getChiTietSanPham().getSanPham().getMa());
        dto.setMaGioHang(chiTietGioHang.get().getGioHang().getMa());
        return Optional.of(dto);
    }
}
