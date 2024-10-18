package com.example.demo.controller;

import com.example.demo.dto.HoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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


//        @GetMapping("/all")
//        public ResponseEntity<Page<HoaDonDTO>> getAllHoaDonWithDetails (
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "5") int size){
//            Pageable pageable = PageRequest.of(page, size);
//            Page<HoaDonDTO> hoaDonList = hoaDonService.getAllHoaDonWithDetails(pageable);
//            return ResponseEntity.ok(hoaDonList);
//        }
//
//        @GetMapping("/chi-tiet")
//        public ResponseEntity<HoaDonDTO> getHoaDonByMaChiTiet (@RequestParam String maHoaDon){
//            Optional<HoaDonDTO> hoaDonDTOOptional = hoaDonService.getHoaDonByMaChiTiet(maHoaDon);
//            return hoaDonDTOOptional
//                    .map(ResponseEntity::ok)
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//        }
//
//        @PostMapping("/add")
//        public ResponseEntity<HoaDon> addHoaDon (@RequestBody HoaDon hoaDon){
//            Optional<HoaDon> hoaDonOptional = hoaDonService.addHoaDon(hoaDon);
//            return hoaDonOptional
//                    .map(ResponseEntity::ok)
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//        }
//
//        @PutMapping("/update")
//        public ResponseEntity<HoaDon> updateHoaDon (@RequestBody HoaDon hoaDon){
//            Optional<HoaDon> hoaDonOptional = hoaDonService.updateHoaDon(hoaDon);
//            return hoaDonOptional
//                    .map(ResponseEntity::ok)
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//        }
//
//        @DeleteMapping("/delete")
//        public ResponseEntity<HoaDon> deleteHoaDonByMaChiTiet (@RequestParam String maHoaDon){
//            Optional<HoaDon> hoaDonOptional = hoaDonService.deleteHoaDon(maHoaDon);
//            return hoaDonOptional
//                    .map(ResponseEntity::ok)
//                    .orElseGet(() -> ResponseEntity.notFound().build());
//
//        }
    }
