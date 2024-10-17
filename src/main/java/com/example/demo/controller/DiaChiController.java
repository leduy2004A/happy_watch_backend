package com.example.demo.controller;

import com.example.demo.model.DiaChi;
import com.example.demo.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/khach-hang")
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("/dia-chi")
    public ResponseEntity<List<DiaChi>> getDiaChiKhachHang() {
        List<DiaChi> diaChiList = diaChiService.getDiaChiKhachHang();
        return ResponseEntity.ok(diaChiList);
    }
}