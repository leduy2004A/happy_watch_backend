package com.example.demo.controller;

import com.example.demo.model.NguoiDung;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService nguoiDungService;

    @GetMapping("/all")
    public ResponseEntity<List<NguoiDung>> getAllKhachHang() {
        List<NguoiDung> khachHangList = nguoiDungService.getAllCustomers();
        return ResponseEntity.ok(khachHangList);
    }
}
