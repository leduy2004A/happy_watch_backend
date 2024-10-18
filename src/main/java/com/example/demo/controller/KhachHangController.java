package com.example.demo.controller;

import com.example.demo.dto.NguoiDungDTO;
import com.example.demo.model.NguoiDung;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/khach-hang")
@CrossOrigin()
public class KhachHangController {
    @Autowired
    private KhachHangService nguoiDungService;

    @GetMapping("/all")
    public ResponseEntity<List<NguoiDungDTO>> getAllKhachHang() {
        List<NguoiDungDTO> khachHangList = nguoiDungService.getAllCustomersWithPhone();
        return ResponseEntity.ok(khachHangList);
    }
}
