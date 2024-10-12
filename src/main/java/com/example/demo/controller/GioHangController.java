package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GioHang;
import com.example.demo.repository.ChiTietGioHangRepository;
import com.example.demo.repository.GioHangRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/gio-hang")
@RequiredArgsConstructor
public class GioHangController {
    private final GioHangRepository gioHangRepository;
    private final ChiTietGioHangRepository chiTietGioHangRepository;

    @GetMapping("/all")
    public ResponseEntity<Page<GioHang>> getAllCarts(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(gioHangRepository.findAll(pageable));
    }

    @GetMapping("/{ma}")
    public ResponseEntity<GioHang> getMethodName(@PathVariable String ma) {
        GioHang gioHang = gioHangRepository.findByMa(ma);
        if (gioHang == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gioHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GioHang> putMethodName(@PathVariable Long id, @RequestBody GioHang gioHang) {
        if (!gioHangRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gioHangRepository.save(gioHang));
    }

    @PostMapping
    public ResponseEntity<GioHang> postMethodName(@RequestBody GioHang gioHang) {
        gioHang.setId(Long.valueOf(0));
        return ResponseEntity.ok(gioHangRepository.save(gioHang));
    }

    @DeleteMapping("/{ma}")
    public ResponseEntity<GioHang> deleteMethodName(@PathVariable String ma) {
        GioHang gioHang = gioHangRepository.findByMa(ma);
        if (gioHang == null) {
            return ResponseEntity.notFound().build();
        }
        gioHangRepository.delete(gioHang);
        return ResponseEntity.ok(gioHang);
    }
    
}