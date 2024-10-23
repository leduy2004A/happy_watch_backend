package com.example.demo.service;

import com.example.demo.appException.AppException;
import com.example.demo.dto.HoaDonDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HoaDonService {
@Autowired
private ChiTietSanPhamRepository chiTietSanPhamRepository;
@Autowired
private ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ThanhToanRepository thanhToanRepository;
    @Autowired
    private DiaChiRepository diaChiRepository;

    public List<HoaDonDTO> getAllHoaDonWithDetails() {

        List<HoaDon> hoaDons = hoaDonRepository.findAll(); // Lấy tất cả hóa đơn
        List<HoaDonDTO> result = new ArrayList<>();

        for (HoaDon hoaDon : hoaDons) {
            NguoiDung nguoiDung = hoaDon.getNguoiDung();
            VaiTro vaiTro = nguoiDung.getVaiTro();
            ThanhToan thanhToan = hoaDon.getThanhToan();
            NguoiDung nguoiTao = hoaDon.getNhanVien();

            HoaDonDTO dto = new HoaDonDTO();
            dto.setHoaDonId(hoaDon.getId());
            dto.setHoaDonMa(hoaDon.getMa());
            dto.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
            dto.setGia(hoaDon.getGia());
            dto.setTinhThanhPho(hoaDon.getTinhThanhPho());
            dto.setQuanHuyen(hoaDon.getQuanHuyen());
            dto.setXaPhuongThiTran(hoaDon.getXaPhuongThiTran());
            dto.setDiaChiCuThe(hoaDon.getDiaChiCuThe());
            dto.setDienThoai(hoaDon.getDienThoai());
            dto.setNgayTao(hoaDon.getNgayTao());
            dto.setLoaiHoaDon(hoaDon.getLoaiHoaDon());
            dto.setTrangThai(hoaDon.getTrangThai());
            dto.setTongCanNang(tinhTongCanNang(hoaDon.getId()));
            dto.setNguoiDungId(nguoiDung.getId());
            dto.setNguoiDungMa(nguoiDung.getMa());
            dto.setNguoiDungTen(nguoiDung.getTen());
            dto.setNguoiDungUsername(nguoiDung.getUsername());

            List<DiaChi> diaChiList = diaChiRepository.findDiaChiByNguoiDungId(nguoiDung.getId());

            dto.setVaiTroId(vaiTro.getId());
            dto.setVaiTroTen(vaiTro.getTenVaiTro());

            dto.setThanhToanId(thanhToan.getId());
            dto.setMaThanhToan(thanhToan.getMa());
            dto.setPhuongThucThanhToan(thanhToan.getPhuongThuc());
            dto.setSoTien(thanhToan.getSoTien());

            dto.setNguoiTaoId(nguoiTao.getId());
            dto.setNguoiTaoMa(nguoiTao.getMa());
            dto.setNguoiTaoTen(nguoiTao.getTen());
            dto.setNguoiTaoUsername(nguoiTao.getUsername());

            result.add(dto);
        }

        return result; // Trả về danh sách DTO
    }


    public Optional<HoaDonDTO> getHoaDonByMaChiTiet(String maChiTiet) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findByMa(maChiTiet);
        if (hoaDonOptional.isPresent()) {
            HoaDon hoaDon = hoaDonOptional.get();
            NguoiDung nguoiDung = hoaDon.getNguoiDung();
            VaiTro vaiTro = nguoiDung.getVaiTro();
            ThanhToan thanhToan = hoaDon.getThanhToan();
            NguoiDung nguoiTao = hoaDon.getNhanVien();

            HoaDonDTO dto = new HoaDonDTO();
            dto.setHoaDonId(hoaDon.getId());
            dto.setHoaDonMa(hoaDon.getMa());
            dto.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
            dto.setGia(hoaDon.getGia());
            dto.setTinhThanhPho(hoaDon.getTinhThanhPho());
            dto.setQuanHuyen(hoaDon.getQuanHuyen());
            dto.setXaPhuongThiTran(hoaDon.getXaPhuongThiTran());
            dto.setDiaChiCuThe(hoaDon.getDiaChiCuThe());
            dto.setNgayTao(hoaDon.getNgayTao());
            dto.setLoaiHoaDon(hoaDon.getLoaiHoaDon());
            dto.setTrangThai(hoaDon.getTrangThai());
            dto.setTongCanNang(tinhTongCanNang(hoaDon.getId()));
            dto.setNguoiDungId(nguoiDung.getId());
            dto.setNguoiDungMa(nguoiDung.getMa());
            dto.setNguoiDungTen(nguoiDung.getTen());
            dto.setNguoiDungUsername(nguoiDung.getUsername());
            dto.setTinhThanhPho(hoaDon.getTinhThanhPho());
            dto.setQuanHuyen(hoaDon.getQuanHuyen());
            dto.setXaPhuongThiTran(hoaDon.getXaPhuongThiTran());
            dto.setDiaChiCuThe(hoaDon.getDiaChiCuThe());
            dto.setDienThoai(hoaDon.getDienThoai());

            dto.setVaiTroId(vaiTro.getId());
            dto.setVaiTroTen(vaiTro.getTenVaiTro());

            dto.setThanhToanId(thanhToan.getId());
            dto.setMaThanhToan(thanhToan.getMa());
            dto.setPhuongThucThanhToan(thanhToan.getPhuongThuc());
            dto.setSoTien(thanhToan.getSoTien());

            dto.setNguoiTaoId(nguoiTao.getId());
            dto.setNguoiTaoMa(nguoiTao.getMa());
            dto.setNguoiTaoTen(nguoiTao.getTen());
            dto.setNguoiTaoUsername(nguoiTao.getUsername());


            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<HoaDon> addHoaDon(HoaDon hoaDon) {
        HoaDon hoaDonNew = new HoaDon();
        String maHoaDon = "HD" + UUID.randomUUID().toString().substring(0, 3);
        hoaDonNew.setMa(maHoaDon);
        hoaDonNew.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
        hoaDonNew.setGia(BigDecimal.valueOf(0.0));
        hoaDonNew.setTinhThanhPho(hoaDon.getTinhThanhPho());
        hoaDonNew.setGia(hoaDon.getGia());
        hoaDonNew.setQuanHuyen(hoaDon.getQuanHuyen());
        hoaDonNew.setXaPhuongThiTran(hoaDon.getXaPhuongThiTran());
        hoaDonNew.setDiaChiCuThe(hoaDon.getDiaChiCuThe());
        hoaDonNew.setDienThoai(hoaDon.getDienThoai());
        LocalDate ngaytao = LocalDate.now();
        hoaDonNew.setNgayTao(ngaytao);
        hoaDonNew.setTrangThai("Pending Payment");
        hoaDonNew.setThanhToan(hoaDon.getThanhToan());
        hoaDonNew.setNhanVien(hoaDon.getNhanVien());
        hoaDonRepository.save(hoaDonNew);
        return Optional.of(hoaDonNew);
    }



    public Optional<HoaDon> updateHoaDon(HoaDon hoaDon) {
        // Tìm hóa đơn theo mã
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findByMa(hoaDon.getMa());

        if (hoaDonOptional.isPresent()) {
            HoaDon existingHoaDon = hoaDonOptional.get();

            // Nếu bạn có ID của NguoiDung, hãy lấy nó từ repository
            if (hoaDon.getNguoiDung() != null && hoaDon.getNguoiDung().getId() != null) {
                Optional<NguoiDung> nguoiDungOpt = nguoiDungRepository.findById(hoaDon.getNguoiDung().getId());
                if (nguoiDungOpt.isPresent()) {
                    existingHoaDon.setNguoiDung(nguoiDungOpt.get());
                } else {
                    // Xử lý trường hợp NguoiDung không tồn tại
                    throw new EntityNotFoundException("NguoiDung not found with id: " + hoaDon.getNguoiDung().getId());
                }
            } else {
                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
                existingHoaDon.setNguoiDung(null);
            }

            if (hoaDon.getThanhToan() != null && hoaDon.getThanhToan().getId() != null) {
                Optional<ThanhToan> thanhToanOptional = thanhToanRepository.findById(hoaDon.getThanhToan().getId());
                if (thanhToanOptional.isPresent()) {
                    existingHoaDon.setThanhToan(thanhToanOptional.get());
                } else {
                    // Xử lý trường hợp NguoiDung không tồn tại
                    throw new EntityNotFoundException("NguoiDung not found with id: " + hoaDon.getNguoiDung().getId());
                }
            } else {
                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
                existingHoaDon.setNguoiDung(null);
            }

            if (hoaDon.getNhanVien() != null && hoaDon.getNhanVien().getId() != null) {
                Optional<NguoiDung> nguoiTaoOptional = nguoiDungRepository.findById(hoaDon.getNhanVien().getId());
                if (nguoiTaoOptional.isPresent()) {
                    existingHoaDon.setNhanVien(nguoiTaoOptional.get());
                } else {
                    // Xử lý trường hợp NguoiDung không tồn tại
                    throw new EntityNotFoundException("NguoiDung not found with id: " + hoaDon.getNhanVien().getId());
                }
            } else {
                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
                existingHoaDon.setNhanVien(null);
            }

            existingHoaDon.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
            existingHoaDon.setGia(hoaDon.getGia());
            existingHoaDon.setTongCanNang(hoaDon.getTongCanNang());
            existingHoaDon.setTinhThanhPho(hoaDon.getTinhThanhPho());
            existingHoaDon.setQuanHuyen(hoaDon.getQuanHuyen());
            existingHoaDon.setXaPhuongThiTran(hoaDon.getXaPhuongThiTran());
            existingHoaDon.setDiaChiCuThe(hoaDon.getDiaChiCuThe());
            existingHoaDon.setDienThoai(hoaDon.getDienThoai());
            existingHoaDon.setTrangThai(hoaDon.getTrangThai());
            existingHoaDon.setLoaiHoaDon(hoaDon.getLoaiHoaDon());
            existingHoaDon.setTongCanNang(hoaDon.getTongCanNang());
            // Lưu lại hóa đơn đã cập nhật
            hoaDonRepository.save(existingHoaDon);

            return Optional.of(existingHoaDon);
        }

        return Optional.empty();
    }

    public Optional<HoaDon> deleteHoaDon(String maHoaDon) {
        // Tìm hóa đơn theo mã
        Optional<HoaDon> hoaDonOptional = hoaDonRepository.findByMa(maHoaDon);

        if (hoaDonOptional.isPresent()) {
            HoaDon existingHoaDon = hoaDonOptional.get();
            // Lưu lại hóa đơn đã cập nhật
            hoaDonRepository.delete(existingHoaDon);

            return Optional.of(existingHoaDon);
        }

        return Optional.empty();
    }


    public HoaDon updateHoaDonStatusToPaid(Long hoaDonId, BigDecimal tienKhachDua, String phuongThuc, BigDecimal gia, String maGiaoDich) {
        HoaDon hoaDon = hoaDonRepository.findById(hoaDonId)
                .orElseThrow(() -> new AppException(404, "Hóa đơn không tồn tại với ID: " + hoaDonId));
        if (hoaDon.getThanhToan() != null) {
            throw new AppException(400, "Hóa đơn đã được thanh toán trước đó và không thể tiếp tục thanh toán.");
        }
        hoaDon.setGia(gia);
        if (phuongThuc.equalsIgnoreCase("chuyển khoản") && (maGiaoDich == null || maGiaoDich.trim().isEmpty())) {
            throw new AppException(400, "Mã giao dịch là bắt buộc khi thanh toán bằng chuyển khoản.");
        }
        if (phuongThuc.equalsIgnoreCase("tiền mặt")) {
            maGiaoDich = null;
        }
        List<ChiTietHoaDon> chiTietHoaDonList = chiTietHoaDonRepository.findByHoaDonId(hoaDonId);
        if (chiTietHoaDonList.isEmpty()) {
            throw new AppException(400, "Hóa đơn không có sản phẩm, không thể thanh toán.");
        }
        if (tienKhachDua.compareTo(hoaDon.getGia()) >= 0) {
            ThanhToan thanhToan = new ThanhToan();
            thanhToan.setMa(UUID.randomUUID().toString());
            thanhToan.setSoTien(tienKhachDua);
            thanhToan.setMaGiaoDich(maGiaoDich);
            ThanhToan savedThanhToan = thanhToanRepository.save(thanhToan);
            hoaDon.setTrangThai("Paid");
            hoaDon.setThanhToan(savedThanhToan);
            for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
                ChiTietSanPham chiTietSanPham = chiTietHoaDon.getChiTietSanPham();
                int soLuongTru = chiTietHoaDon.getSoLuong();
                chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - soLuongTru);
                chiTietSanPhamRepository.save(chiTietSanPham);
            }
            return hoaDonRepository.save(hoaDon);
        } else {
            throw new AppException(400, "Số tiền khách đưa không đủ để thanh toán hóa đơn.");
        }
    }






    public List<HoaDon> getHoaDonsChoThanhToanByNguoiDungId(Long idNguoiDung) {
        return hoaDonRepository.findHoaDonByNguoiDungIdAndTrangThaiChoThanhToan(idNguoiDung);
    }


    public List<HoaDon> getAllHoaDonsChoThanhToan() {//Nháp
        return hoaDonRepository.findAllHoaDonChoThanhToan();
    }


    public List<HoaDon> getAllHoaDonsPaid() {
        return hoaDonRepository.findAllHoaDonPaid();
    }


    // huủy hóa đơn
    @Transactional
    public boolean cancelHoaDon(Long id) {
        int updatedRows = hoaDonRepository.updateTrangThaiHoaDonToCancelled(id);
        return updatedRows > 0;
    }

    // lấy tất cả hóa đơn có trạng thái đã hủy
    public List<HoaDon> getAllHoaDonsCancelled() {
        return hoaDonRepository.findAllHoaDonCancelled();
    }

    // Phương thức cập nhật trạng thái hóa đơn thành "Confirmed"
    @Transactional
    public boolean confirmHoaDonWithAddress(Long idHoaDon, String tenNguoiNhan ,String tinhThanhPho, String quanHuyen,
                                            String xaPhuongThiTran, String diaChiCuThe, String dienThoai) {

        int updatedRows = hoaDonRepository.updateHoaDonToConfirmedWithAddress(
                idHoaDon,
                tenNguoiNhan,
                tinhThanhPho,
                quanHuyen,
                xaPhuongThiTran,
                diaChiCuThe,
                dienThoai
        );
        return updatedRows > 0; // Trả về true nếu cập nhật thành công
    }

    public int tinhTongCanNang(Long idHoaDon) {
        Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(idHoaDon);

        if (hoaDonOpt.isPresent()) {
            HoaDon hoaDon = hoaDonOpt.get();
            return hoaDon.getChiTietHoaDonList()
                    .stream()
                    .mapToInt(cthd -> cthd.getChiTietSanPham().getCanNang() * cthd.getSoLuong())
                    .sum();
        } else {
            throw new EntityNotFoundException("Không tìm thấy hóa đơn với ID: " + idHoaDon);
        }
    }

//truyền loại hóa đơn
    public HoaDon updateLoaiHoaDon(Long id, String loaiHoaDon) {
        HoaDon hoaDon = hoaDonRepository.findById(id)
                .orElseThrow(() -> new AppException(404, "Hóa đơn không tồn tại với ID: " + id));
        hoaDon.setLoaiHoaDon(loaiHoaDon);
        return hoaDonRepository.save(hoaDon);
    }
}
