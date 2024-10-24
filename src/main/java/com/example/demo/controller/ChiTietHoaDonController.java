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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/chitiethoadon")
@CrossOrigin()
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @GetMapping("/all")
    public ResponseEntity<List<ChiTietHoaDonDTO>> getAllChiTietHoaDonWithDetails() {
        List<ChiTietHoaDonDTO> chiTietHoaDonList = chiTietHoaDonService.getAllChiTietHoaDonWithDetails();
        return ResponseEntity.ok(chiTietHoaDonList);
    }


    @GetMapping("/chi-tiet")
    public ResponseEntity<ChiTietHoaDonDTO> getHoaDonByMaChiTiet(@RequestParam("id") Long id) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonDTOOptional = chiTietHoaDonService.getChiTietHoaDonById(id);
        return chiTietHoaDonDTOOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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



    @PostMapping("/add")
    public ResponseEntity<ChiTietHoaDonDTO> addHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonOptional = chiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);
        return chiTietHoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<ChiTietHoaDonDTO> updateHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonOptional = chiTietHoaDonService.updateChiTietHoaDon(chiTietHoaDon);
        return chiTietHoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/congsl")
    public ResponseEntity<ChiTietHoaDonDTO> congSoLuongSanPhamHoaDon(@RequestParam("idCTHD") Long idCTHD) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonOptional = chiTietHoaDonService.congSoLuongSanPhamHoaDon(idCTHD);
        return chiTietHoaDonOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/trusl")
    public ResponseEntity<ChiTietHoaDonDTO> truSoLuongSanPhamHoaDon(@RequestParam("idCTHD") Long idCTHD) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonOptional = chiTietHoaDonService.truSoLuongSanPhamHoaDon(idCTHD);
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
