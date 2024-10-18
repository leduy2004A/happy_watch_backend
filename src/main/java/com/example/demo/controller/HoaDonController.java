package com.example.demo.controller;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.dto.HoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.service.ChiTietHoaDonService;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/hoa-don")

public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

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

    @GetMapping("/all")
    public ResponseEntity<List<HoaDonDTO>> getAllHoaDonWithDetails() {
        List<HoaDonDTO> hoaDonList = hoaDonService.getAllHoaDonWithDetails();
        return ResponseEntity.ok(hoaDonList);
    }

    @GetMapping("/all-sp")
    public ResponseEntity<Map<String, Object>> getAllChiTietHoaDon(@RequestParam("idHD") Long idHD) {
        // Gọi phương thức trong service để lấy danh sách chi tiết hóa đơn và tổng tiền
        Map<String, Object> chiTietHoaDonData = chiTietHoaDonService.getChiTietHoaDon(idHD);

        if (((List<ChiTietHoaDonDTO>) chiTietHoaDonData.get("chiTietHoaDons")).isEmpty()) {
            return ResponseEntity.notFound().build(); // Nếu danh sách trống, trả về 404 Not Found
        }

        return ResponseEntity.ok(chiTietHoaDonData); // Trả về dữ liệu gồm chi tiết hóa đơn và tổng tiền
    }

    @GetMapping("/chi-tiet")
    public ResponseEntity<HoaDonDTO> getHoaDonByMaChiTiet(@RequestParam String maHoaDon) {
        Optional<HoaDonDTO> hoaDonDTOOptional = hoaDonService.getHoaDonByMaChiTiet(maHoaDon);
        return hoaDonDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<HoaDon> addHoaDon(@RequestBody HoaDon hoaDon) {
        Optional<HoaDon> hoaDonOptional = hoaDonService.addHoaDon(hoaDon);
        return hoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<HoaDon> updateHoaDon(@RequestBody HoaDon hoaDon) {
        Optional<HoaDon> hoaDonOptional = hoaDonService.updateHoaDon(hoaDon);
        return hoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HoaDon> deleteHoaDonByMaChiTiet(@RequestParam String maHoaDon) {
        Optional<HoaDon> hoaDonOptional = hoaDonService.deleteHoaDon(maHoaDon);
        return hoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    //hiển thị hóa đơn chưa thanh toán
    @GetMapping("/cho-thanh-toan/{idNguoiDung}")
    public ResponseEntity<List<HoaDon>> getHoaDonChoThanhToan(@PathVariable Long idNguoiDung) {
        List<HoaDon> hoaDonList = hoaDonService.getHoaDonsChoThanhToanByNguoiDungId(idNguoiDung);
        return ResponseEntity.ok(hoaDonList);
    }

    //hiển thị hóa đơn chưa thanh toán
    @GetMapping("/cho-thanh-toan")//Nháppppppppppp
    public ResponseEntity<List<HoaDon>> getAllHoaDonChoThanhToan() {
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDonsChoThanhToan();
        return ResponseEntity.ok(hoaDonList);
    }

    //hiển thị hóa đơn đã thanh toán
    @GetMapping("/paid")
    public ResponseEntity<List<HoaDon>> getAllHoaDonPaid() {
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDonsPaid();
        return ResponseEntity.ok(hoaDonList);
    }
}
