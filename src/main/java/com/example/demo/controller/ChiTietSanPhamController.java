package com.example.demo.controller;

import com.example.demo.model.ChiTietSanPham;
import com.example.demo.service.ChiTietSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chi-tiet-san-pham")
public class ChiTietSanPhamController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @PostMapping("/add-ctsp")
    public ResponseEntity<ChiTietSanPham> addChiTietSanPham(@RequestBody ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham createdChiTietSanPham = chiTietSanPhamService.addChiTietSanPham(chiTietSanPham);
        return new ResponseEntity<>(createdChiTietSanPham, HttpStatus.OK);
    }
    @PutMapping("/{ma}")
    public ResponseEntity<ChiTietSanPham> updateChiTietSanPham(
            @PathVariable String ma,
            @RequestBody ChiTietSanPham chiTietSanPham) {
        ChiTietSanPham updatedChiTietSanPham = chiTietSanPhamService.updateChiTietSanPham(ma, chiTietSanPham);
        return new ResponseEntity<>(updatedChiTietSanPham, HttpStatus.OK);
    }

}
