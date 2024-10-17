package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/hoa-don")

public class HoaDonController {
    @Autowired
    private HoaDonService hoaDonService;

    @PutMapping("/update-status/{id}")
    public ResponseEntity<HoaDon> updateHoaDonStatusToPaid(
            @PathVariable Long id,
            @RequestParam BigDecimal tienKhachDua,
            @RequestParam String phuongThuc) {
        HoaDon updatedHoaDon = hoaDonService.updateHoaDonStatusToPaid(id, tienKhachDua, phuongThuc);
        return ResponseEntity.ok(updatedHoaDon);
    }

    @PutMapping("/update/khach-hang/{idHoaDon}")
    public ResponseEntity<HoaDon> updateHoaDon(
            @PathVariable Long idHoaDon,
            @RequestParam Long idNguoiDung) {
        HoaDon updatedHoaDon = hoaDonService.updateHoaDonWithNguoiDung(idHoaDon, idNguoiDung);
        return ResponseEntity.ok(updatedHoaDon);
    }

}
