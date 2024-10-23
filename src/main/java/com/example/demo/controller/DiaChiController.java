package com.example.demo.controller;

import com.example.demo.appException.AppException;
import com.example.demo.model.DiaChi;
import com.example.demo.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/khach-hang")
@CrossOrigin
public class DiaChiController {

    @Autowired
    private DiaChiService diaChiService;

    @GetMapping("/dia-chi/{id}")
    public ResponseEntity<List<DiaChi>> getDiaChiByNguoiDungId(@PathVariable("id") Long idNguoiDung) {
        List<DiaChi> diaChis = diaChiService.getDiaChiByNguoiDungId(idNguoiDung);
        return ResponseEntity.ok(diaChis);
    }

    @PutMapping("/dia-chi/first/{id}")
    public ResponseEntity<?> updateHoaDonWithNguoiDungAndGetFirstDiaChi(
            @PathVariable Long id,
            @RequestParam Long idNguoiDung) {
        try {
            DiaChi diaChi = diaChiService.updateHoaDonWithNguoiDungAndGetFirstDiaChi(id, idNguoiDung);
            return ResponseEntity.ok(diaChi);
        } catch (AppException e) {
            return ResponseEntity.status(e.getCode()).body(e.getMessage());
        }
    }

    @GetMapping("/{idNguoiDung}/diachi/{idDiaChi}")
    public ResponseEntity<DiaChi> getDiaChiByNguoiDungIdAndDiaChiId(
            @PathVariable("idNguoiDung") Long idNguoiDung,
            @PathVariable("idDiaChi") Long idDiaChi) {

        Optional<DiaChi> diaChi = diaChiService.getDiaChiByNguoiDungIdAndDiaChiId(idNguoiDung, idDiaChi);

        return diaChi.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add/{idNguoiDung}")
    public ResponseEntity<?> addDiaChiForNguoiDung(
            @PathVariable Long idNguoiDung,
            @RequestBody DiaChi diaChi) {
        try {
            DiaChi savedDiaChi = diaChiService.addDiaChiForNguoiDung(idNguoiDung, diaChi);
            return ResponseEntity.ok(savedDiaChi);
        } catch (AppException ex) {
            return ResponseEntity.status(ex.getCode()).body(ex.getMessage());
        }
    }
}