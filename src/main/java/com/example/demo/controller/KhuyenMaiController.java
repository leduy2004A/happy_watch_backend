package com.example.demo.controller;

import com.example.demo.dto.KhuyenMaiDTO;
import com.example.demo.model.KhuyenMai;
import com.example.demo.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khuyenmai")
public class KhuyenMaiController {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    // Thêm mới khuyến mãi
    @PostMapping("/add")
    public ResponseEntity<KhuyenMai> addKhuyenMai(@RequestBody KhuyenMai khuyenMai) {
        KhuyenMai newKhuyenMai = khuyenMaiService.addKhuyenMai(khuyenMai);
        return new ResponseEntity<>(newKhuyenMai, HttpStatus.CREATED);
    }

    // Lấy tất cả khuyến mãi
    @GetMapping("/all")
    public ResponseEntity<List<KhuyenMai>> getAllKhuyenMai() {
        List<KhuyenMai> khuyenMais = khuyenMaiService.getAllKhuyenMai();
        return new ResponseEntity<>(khuyenMais, HttpStatus.OK);
    }

    // Tìm khuyến mãi theo mã
    @GetMapping("/find/{ma}")
    public ResponseEntity<KhuyenMai> getKhuyenMaiByMa(@PathVariable String ma) {
        Optional<KhuyenMai> khuyenMai = khuyenMaiService.getKhuyenMaiByMa(ma);
        if (khuyenMai.isPresent()) {
            return new ResponseEntity<>(khuyenMai.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Cập nhật khuyến mãi
    @PutMapping("/update/{ma}")
    public ResponseEntity<KhuyenMai> updateKhuyenMai(@PathVariable String ma, @RequestBody KhuyenMai khuyenMai) {
        KhuyenMai updatedKhuyenMai = khuyenMaiService.updateKhuyenMaiByMa(ma, khuyenMai);
        if (updatedKhuyenMai != null) {
            return new ResponseEntity<>(updatedKhuyenMai, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Thêm mới khuyến mãi cho nhiều sản phẩm cùng lúc
    @PostMapping("/create")
    public ResponseEntity<KhuyenMai> createKhuyenMai(@RequestBody KhuyenMaiDTO khuyenMaiDTO) {
        KhuyenMai khuyenMai = khuyenMaiService.createKhuyenMai(khuyenMaiDTO);
        return new ResponseEntity<>(khuyenMai, HttpStatus.CREATED);
    }
}

