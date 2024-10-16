package com.example.demo.service;

import com.example.demo.model.ChiTietHoaDon;
import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.HoaDon;
import com.example.demo.model.ThanhToan;
import com.example.demo.repository.ChiTietHoaDonRepository;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;
    public HoaDon updateHoaDonStatusToPaid(Long hoaDonId, BigDecimal tienKhachDua, String phuongThuc) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại với ID: " + hoaDonId));
        if (tienKhachDua.compareTo(hoaDon.getGia()) >= 0) {
            ThanhToan thanhToan = new ThanhToan();
            thanhToan.setMa(UUID.randomUUID().toString());
            thanhToan.setPhuongThuc(phuongThuc);
            thanhToan.setSoTien(tienKhachDua);
            ThanhToan savedThanhToan = thanhToanRepository.save(thanhToan);
            hoaDon.setTrangThai("Đã Thanh Toán");
            hoaDon.setThanhToan(savedThanhToan);
            List<ChiTietHoaDon> chiTietHoaDonList = chiTietHoaDonRepository.findByHoaDonId(hoaDonId);
            for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
                ChiTietSanPham chiTietSanPham = chiTietHoaDon.getChiTietSanPham();
                int soLuongTru = chiTietHoaDon.getSoLuong();
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - soLuongTru);
                chiTietSanPhamRepository.save(chiTietSanPham);
            }
            return hoaDonRepository.save(hoaDon);
        } else {
            throw new RuntimeException("Số tiền khách đưa không đủ để thanh toán hóa đơn.");
        }
    }
}
