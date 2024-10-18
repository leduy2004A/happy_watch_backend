package com.example.demo.controller;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GioHang;
import com.example.demo.repository.GioHangRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class GioHangController {

    private final GioHangRepository gioHangRepository;

    @GetMapping()
    public ResponseEntity<Page<GioHang>> getAllCarts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(gioHangRepository.findAll(pageable));
    }

    @GetMapping("/{cartCode}")
    public ResponseEntity<GioHang> getCart(@PathVariable String cartCode) {
        GioHang gioHang = gioHangRepository.findByMa(cartCode);
        if (gioHang == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gioHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GioHang> updateCart(@PathVariable Long id, @RequestBody GioHang gioHang) {
        if (!gioHangRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gioHangRepository.save(gioHang));
    }

    @PostMapping
    public ResponseEntity<GioHang> saveCart(@RequestBody GioHang gioHang) {
        gioHang.setId(Long.valueOf(0));
        gioHang.setTongGia(BigDecimal.valueOf(0));
        gioHang.setTongSoLuong(Integer.valueOf(0));
        return ResponseEntity.ok(gioHangRepository.save(gioHang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GioHang> deleteCart(@PathVariable Long id) {
        GioHang gioHang = gioHangRepository.findById(id).orElse(null);
        if (gioHang == null) {
            return ResponseEntity.notFound().build();
        }
        gioHangRepository.delete(gioHang);
        return ResponseEntity.ok(gioHang);
    }
}