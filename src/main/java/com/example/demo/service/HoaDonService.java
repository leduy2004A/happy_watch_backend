package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ChiTietHoaDon;
import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.HoaDon;
import com.example.demo.model.NguoiDung;
import com.example.demo.model.ThanhToan;
import com.example.demo.repository.ChiTietHoaDonRepository;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.NguoiDungRepository;
import com.example.demo.repository.ThanhToanRepository;

@Service
public class HoaDonService {
@Autowired
private ChiTietSanPhamRepository  chiTietSanPhamRepository;
@Autowired
private ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ThanhToanRepository thanhToanRepository;

//    public Page<HoaDonDTO> getAllHoaDonWithDetails(Pageable pageable) {
//
//        Page<HoaDon> hoaDons = hoaDonRepository.findAll(pageable);
//        List<HoaDonDTO> result = new ArrayList<>();
//
//        for (HoaDon hoaDon : hoaDons.getContent()) {
//            NguoiDung nguoiDung = hoaDon.getNguoiDung();
//            VaiTro vaiTro = nguoiDung.getVaiTro();
//            ThanhToan thanhToan = hoaDon.getThanhToan();
//            NguoiDung nguoiTao = hoaDon.getNhanVien();
//
//            HoaDonDTO dto = new HoaDonDTO();
//            dto.setHoaDonId(hoaDon.getId());
//            dto.setHoaDonMa(hoaDon.getMa());
//            dto.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
//            dto.setGia(hoaDon.getGia());
//            dto.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
//            dto.setNgayTao(hoaDon.getNgayTao());
//            dto.setTrangThai(hoaDon.getTrangThai());
//
//            dto.setNguoiDungId(nguoiDung.getId());
//            dto.setNguoiDungMa(nguoiDung.getMa());
//            dto.setNguoiDungTen(nguoiDung.getTen());
//            dto.setNguoiDungUsername(nguoiDung.getUsername());
//            dto.setNguoiDungDienThoai(nguoiDung.getDienThoai());
//
//            dto.setVaiTroId(vaiTro.getId());
//            dto.setVaiTroTen(vaiTro.getTenVaiTro());
//
//            dto.setThanhToanId(thanhToan.getId());
//            dto.setMaThanhToan(thanhToan.getMa());
//            dto.setPhuongThucThanhToan(thanhToan.getPhuongThuc());
//            dto.setSoTien(thanhToan.getSoTien());
//
//            dto.setNguoiTaoId(nguoiTao.getId());
//            dto.setNguoiTaoMa(nguoiTao.getMa());
//            dto.setNguoiTaoTen(nguoiTao.getTen());
//            dto.setNguoiTaoUsername(nguoiTao.getUsername());
//
//
//            result.add(dto);
//        }
//        return new PageImpl<>(result, pageable, hoaDons.getTotalElements());
//    }
//
//    public Optional<HoaDonDTO> getHoaDonByMaChiTiet(String maChiTiet) {
//        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findByMa(maChiTiet);
//        if (hoaDonOptional.isPresent()) {
//            HoaDon hoaDon = hoaDonOptional.get();
//            NguoiDung nguoiDung = hoaDon.getNguoiDung();
//            VaiTro vaiTro = nguoiDung.getVaiTro();
//            ThanhToan thanhToan = hoaDon.getThanhToan();
//            NguoiDung nguoiTao = hoaDon.getNhanVien();
//
//            HoaDonDTO dto = new HoaDonDTO();
//            dto.setHoaDonId(hoaDon.getId());
//            dto.setHoaDonMa(hoaDon.getMa());
//            dto.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
//            dto.setGia(hoaDon.getGia());
//            dto.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
//            dto.setNgayTao(hoaDon.getNgayTao());
//            dto.setTrangThai(hoaDon.getTrangThai());
//
//            dto.setNguoiDungId(nguoiDung.getId());
//            dto.setNguoiDungMa(nguoiDung.getMa());
//            dto.setNguoiDungTen(nguoiDung.getTen());
//            dto.setNguoiDungUsername(nguoiDung.getUsername());
//            dto.setNguoiDungDienThoai(nguoiDung.getDienThoai());
//
//            dto.setVaiTroId(vaiTro.getId());
//            dto.setVaiTroTen(vaiTro.getTenVaiTro());
//
//            dto.setThanhToanId(thanhToan.getId());
//            dto.setMaThanhToan(thanhToan.getMa());
//            dto.setPhuongThucThanhToan(thanhToan.getPhuongThuc());
//            dto.setSoTien(thanhToan.getSoTien());
//
//            dto.setNguoiTaoId(nguoiTao.getId());
//            dto.setNguoiTaoMa(nguoiTao.getMa());
//            dto.setNguoiTaoTen(nguoiTao.getTen());
//            dto.setNguoiTaoUsername(nguoiTao.getUsername());
//
//            return Optional.of(dto);
//        }
//        return Optional.empty();
//    }
//
//    public Optional<HoaDon> addHoaDon(HoaDon hoaDon) {
//        HoaDon hoaDonNew = new HoaDon();
//        hoaDonNew.setMa(hoaDon.getMa());
//        hoaDonNew.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
//        hoaDonNew.setGia(BigDecimal.valueOf(0.0));
//        hoaDonNew.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
//        hoaDonNew.setNgayTao(hoaDon.getNgayTao());
//        hoaDonNew.setTrangThai(hoaDon.getTrangThai());
//        hoaDonNew.setThanhToan(hoaDon.getThanhToan());
//        hoaDonNew.setNhanVien(hoaDon.getNhanVien());
//
//        // Lưu vào cơ sở dữ liệu
//        hoaDonRepository.save(hoaDonNew);
//
//        return Optional.of(hoaDon);
//    }
//
//
//
//    public Optional<HoaDon> updateHoaDon(HoaDon hoaDon) {
//        // Tìm hóa đơn theo mã
//        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findByMa(hoaDon.getMa());
//
//        if (hoaDonOptional.isPresent()) {
//            HoaDon existingHoaDon = hoaDonOptional.get();
//
//            // Nếu bạn có ID của NguoiDung, hãy lấy nó từ repository
//            if (hoaDon.getNguoiDung() != null && hoaDon.getNguoiDung().getId() != null) {
//                Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findById(hoaDon.getNguoiDung().getId());
//                if (nguoiDungOpt.isPresent()) {
//                    existingHoaDon.setNguoiDung(nguoiDungOpt.get());
//                } else {
//                    // Xử lý trường hợp NguoiDung không tồn tại
//                    throw new EntityNotFoundException("NguoiDung not found with id: " + hoaDon.getNguoiDung().getId());
//                }
//            } else {
//                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
//                existingHoaDon.setNguoiDung(null);
//            }
//
//            if (hoaDon.getThanhToan() != null && hoaDon.getThanhToan().getId() != null) {
//                Optional<ThanhToan> thanhToanOptional = thanhToanRepository.findById(hoaDon.getThanhToan().getId());
//                if (thanhToanOptional.isPresent()) {
//                    existingHoaDon.setThanhToan(thanhToanOptional.get());
//                } else {
//                    // Xử lý trường hợp NguoiDung không tồn tại
//                    throw new EntityNotFoundException("NguoiDung not found with id: " + hoaDon.getNguoiDung().getId());
//                }
//            } else {
//                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
//                existingHoaDon.setNguoiDung(null);
//            }
//
//            if (hoaDon.getNhanVien() != null && hoaDon.getNhanVien().getId() != null) {
//                Optional<NguoiDung> nguoiTaoOptional = nguoiDungRepository.findById(hoaDon.getNhanVien().getId());
//                if (nguoiTaoOptional.isPresent()) {
//                    existingHoaDon.setNhanVien(nguoiTaoOptional.get());
//                } else {
//                    // Xử lý trường hợp NguoiDung không tồn tại
//                    throw new EntityNotFoundException("NguoiDung not found with id: " + hoaDon.getNhanVien().getId());
//                }
//            } else {
//                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
//                existingHoaDon.setNhanVien(null);
//            }
//
//            existingHoaDon.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
//            existingHoaDon.setGia(hoaDon.getGia());
//            existingHoaDon.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
//            existingHoaDon.setNgayTao(hoaDon.getNgayTao());
//            existingHoaDon.setTrangThai(hoaDon.getTrangThai());
//
//            // Lưu lại hóa đơn đã cập nhật
//            hoaDonRepository.save(existingHoaDon);
//
//            return Optional.of(existingHoaDon);
//        }
//
//        return Optional.empty();
//    }
//
//    public Optional<HoaDon> deleteHoaDon(String maHoaDon) {
//        // Tìm hóa đơn theo mã
//        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findByMa(maHoaDon);
//
//        if (hoaDonOptional.isPresent()) {
//            HoaDon existingHoaDon = hoaDonOptional.get();
//            // Lưu lại hóa đơn đã cập nhật
//            hoaDonRepository.delete(existingHoaDon);
//
//            return Optional.of(existingHoaDon);
//        }
//
//        return Optional.empty();
//    }
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

    public HoaDon updateHoaDonWithNguoiDung(Long idHoaDon, Long idNguoiDung) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        NguoiDung nguoiDung = nguoiDungRepository.findById(idNguoiDung)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        hoaDon.setNguoiDung(nguoiDung);
        return hoaDonRepository.save(hoaDon);
    }

}
