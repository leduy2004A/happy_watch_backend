package com.example.demo.controller;

import com.example.demo.dto.ThanhToanDTO;
import com.example.demo.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/thanh-toan")
public class ThanhToanController {
    @Autowired
    private ThanhToanService thanhToanService;

    @GetMapping("/all")
    public ResponseEntity<List<ThanhToanDTO>> getAllThanhToanWithHoaDonMa() {
        List<ThanhToanDTO> thanhToanList = thanhToanService.getAllThanhToanWithHoaDonMa();
        return ResponseEntity.ok(thanhToanList);
    }
}
