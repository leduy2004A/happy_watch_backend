package com.example.demo.controller;

import com.example.demo.dto.SanPhamChiTietDTO;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sanpham")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/all")
    public ResponseEntity<Page<SanPhamChiTietDTO>> getAllSanPhamWithDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SanPhamChiTietDTO> sanPhamChiTietDTOList = sanPhamService.getAllSanPhamWithDetails(pageable);
        return ResponseEntity.ok(sanPhamChiTietDTOList);
    }

    @GetMapping("/chi-tiet")
    public ResponseEntity<SanPhamChiTietDTO> getSanPhamByMaChiTiet(@RequestParam String maChiTiet) {
        Optional<SanPhamChiTietDTO> sanPhamChiTietDTOOptional = sanPhamService.getSanPhamByMaChiTiet(maChiTiet);
        return sanPhamChiTietDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
