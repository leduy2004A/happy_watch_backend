package com.example.demo.controller;

import com.example.demo.model.KhuyenMai;
import com.example.demo.model.KhuyenMaiHoaDon;
import com.example.demo.service.KhuyenMaiHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/khuyenmaihoadon")
public class KhuyenMaiHoaDonController {
    @Autowired
    private KhuyenMaiHoaDonService khuyenMaiHoaDonService;
    @PostMapping("/add")
    public ResponseEntity<KhuyenMaiHoaDon> addKhuyenMai(@RequestBody KhuyenMaiHoaDon khuyenMaiHoaDon) {
        KhuyenMaiHoaDon newKhuyenMai = khuyenMaiHoaDonService.addKhuyenMai(khuyenMaiHoaDon);
        return new ResponseEntity<>(newKhuyenMai, HttpStatus.OK);
    }
}
