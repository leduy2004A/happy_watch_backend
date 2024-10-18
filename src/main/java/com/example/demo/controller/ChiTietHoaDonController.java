package com.example.demo.controller;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.model.ChiTietHoaDon;
import com.example.demo.service.ChiTietHoaDonService;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/chitiethoadon")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @GetMapping("/all")
    public ResponseEntity<Page<ChiTietHoaDonDTO>> getAllChiTietHoaDonWithDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietHoaDonDTO> chiTietHoaDonList = chiTietHoaDonService.getAllChiTietHoaDonWithDetails(pageable);
        return ResponseEntity.ok(chiTietHoaDonList);
    }

    @GetMapping("/chi-tiet")
    public ResponseEntity<ChiTietHoaDonDTO> getHoaDonByMaChiTiet(@RequestParam Long id) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonDTOOptional = chiTietHoaDonService.getChiTietHoaDonById(id);
        return chiTietHoaDonDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ChiTietHoaDon> addHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon) {
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);
        return chiTietHoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ChiTietHoaDon> updateHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon, @RequestParam("giaTungSanPham") BigDecimal giaTungSanPham) {
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonService.updateChiTietHoaDon(chiTietHoaDon, giaTungSanPham);
        return chiTietHoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ChiTietHoaDon> deleteHoaDonByMaChiTiet(@RequestParam Long id) {
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonService.deleteChiTietHoaDon(id);
        return chiTietHoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
