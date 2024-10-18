package com.example.demo.controller;

import com.example.demo.model.DiaChi;
import com.example.demo.service.DiaChiService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/dia-chi/first/{id}")
    public ResponseEntity<DiaChi> getFirstDiaChiByNguoiDungId(@PathVariable("id") Long idNguoiDung) {
        Optional<DiaChi> diaChi = diaChiService.getFirstDiaChiByNguoiDungId(idNguoiDung);
        return diaChi.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{idNguoiDung}/diachi/{idDiaChi}")
    public ResponseEntity<DiaChi> getDiaChiByNguoiDungIdAndDiaChiId(
            @PathVariable("idNguoiDung") Long idNguoiDung,
            @PathVariable("idDiaChi") Long idDiaChi) {

        Optional<DiaChi> diaChi = diaChiService.getDiaChiByNguoiDungIdAndDiaChiId(idNguoiDung, idDiaChi);

        return diaChi.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}