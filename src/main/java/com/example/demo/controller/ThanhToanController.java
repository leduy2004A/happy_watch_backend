package com.example.demo.controller;

import com.example.demo.dto.ThanhToanDTO;
import com.example.demo.model.ThanhToan;
import com.example.demo.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    //hiển thị dữ liệu thanh toán dựa vào id hóa đơn
    @GetMapping("/hien-thi/{hoaDonId}/")
    public ResponseEntity<?> getThanhToanByHoaDonId(@PathVariable Long hoaDonId) {
        Optional<ThanhToan> thanhToanOpt = thanhToanService.getThanhToanByHoaDonId(hoaDonId);
        if (thanhToanOpt.isPresent()) {
            return ResponseEntity.ok(thanhToanOpt.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
