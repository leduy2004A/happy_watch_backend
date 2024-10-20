package com.example.demo.controller;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.dto.HoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.service.ChiTietHoaDonService;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/hoadon")
@CrossOrigin()
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietHoaDonService chiTietHoaDonService;

    @PutMapping("/update-status/{id}")
    public ResponseEntity<HoaDon> updateHoaDonStatusToPaid(
            @PathVariable Long id,
            @RequestParam BigDecimal tienKhachDua,
            @RequestParam String phuongThuc,
            @RequestParam String maGiaoDich,
            @RequestParam BigDecimal gia) {
        HoaDon updatedHoaDon = hoaDonService.updateHoaDonStatusToPaid(id, tienKhachDua, phuongThuc, gia,maGiaoDich);
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


    // cập nhật trạng thái hóa đơn thành "Cancelled"
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelHoaDon(@PathVariable Long id) {
        boolean isUpdated = hoaDonService.cancelHoaDon(id);
        if (isUpdated) {
            return ResponseEntity.ok("Hóa đơn đã được hủy thành công.");
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy hóa đơn hoặc không thể cập nhật.");
        }
    }

    // hiển thị hóa đơn đã hủy
    @GetMapping("/cancelled")
    public ResponseEntity<List<HoaDon>> getAllHoaDonCancelled() {
        List<HoaDon> hoaDonList = hoaDonService.getAllHoaDonsCancelled();
        return ResponseEntity.ok(hoaDonList);
    }

    // cập nhật trạng thái hóa đơn thành đã xác nhận
    @PutMapping("/confirm-with-address/{idHoaDon}")
    public ResponseEntity<String> confirmHoaDonWithAddress(
            @PathVariable Long idHoaDon,
            @RequestParam String tenNguoiNhan,
            @RequestParam String tinhThanhPho,
            @RequestParam String quanHuyen,
            @RequestParam String xaPhuongThiTran,
            @RequestParam String diaChiCuThe,
            @RequestParam String dienThoai) {

        boolean isUpdated = hoaDonService.confirmHoaDonWithAddress(
                idHoaDon,
                tenNguoiNhan,
                tinhThanhPho,
                quanHuyen,
                xaPhuongThiTran,
                diaChiCuThe,
                dienThoai
        );

        if (isUpdated) {
            return ResponseEntity.ok("Hóa đơn đã được xác nhận và địa chỉ đã được cập nhật.");
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy hóa đơn hoặc không thể cập nhật địa chỉ.");
        }
    }
}
