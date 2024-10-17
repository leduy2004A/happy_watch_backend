package com.example.demo.service;

import com.example.demo.dto.HoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.model.NguoiDung;
import com.example.demo.model.ThanhToan;
import com.example.demo.model.VaiTro;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.NguoiDungRepository;
import com.example.demo.repository.ThanhToanRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public Page<HoaDonDTO> getAllHoaDonWithDetails(Pageable pageable) {

        Page<HoaDon> hoaDons = hoaDonRepository.findAll(pageable);
        List<HoaDonDTO> result = new ArrayList<>();

        for (HoaDon hoaDon : hoaDons.getContent()) {
            NguoiDung nguoiDung = hoaDon.getNguoiDung();
            VaiTro vaiTro = nguoiDung.getVaiTro();
            ThanhToan thanhToan = hoaDon.getThanhToan();
            NguoiDung nguoiTao = hoaDon.getNhanVien();

            HoaDonDTO dto = new HoaDonDTO();
            dto.setHoaDonId(hoaDon.getId());
            dto.setHoaDonMa(hoaDon.getMa());
            dto.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
            dto.setGia(hoaDon.getGia());
            dto.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
            dto.setNgayTao(hoaDon.getNgayTao());
            dto.setTrangThai(hoaDon.getTrangThai());

            dto.setNguoiDungId(nguoiDung.getId());
            dto.setNguoiDungMa(nguoiDung.getMa());
            dto.setNguoiDungTen(nguoiDung.getTen());
            dto.setNguoiDungUsername(nguoiDung.getUsername());
            dto.setNguoiDungDienThoai(nguoiDung.getDienThoai());

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
        return new PageImpl<>(result, pageable, hoaDons.getTotalElements());
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
            dto.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
            dto.setNgayTao(hoaDon.getNgayTao());
            dto.setTrangThai(hoaDon.getTrangThai());

            dto.setNguoiDungId(nguoiDung.getId());
            dto.setNguoiDungMa(nguoiDung.getMa());
            dto.setNguoiDungTen(nguoiDung.getTen());
            dto.setNguoiDungUsername(nguoiDung.getUsername());
            dto.setNguoiDungDienThoai(nguoiDung.getDienThoai());

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
        hoaDonNew.setId(hoaDon.getId());


        String maHoaDon = "HD" + UUID.randomUUID().toString().substring(0, 3); // Tạo mã hóa đơn với số định dạng 5 chữ số

        hoaDonNew.setMa(maHoaDon);
        hoaDonNew.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
        hoaDonNew.setGia(BigDecimal.valueOf(0.0));
        hoaDonNew.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());

        LocalDate ngaytao = LocalDate.now();

        hoaDonNew.setNgayTao(ngaytao);
        hoaDonNew.setTrangThai(hoaDon.getTrangThai());
        hoaDonNew.setThanhToan(hoaDon.getThanhToan());
        hoaDonNew.setNhanVien(hoaDon.getNhanVien());

        // Lưu vào cơ sở dữ liệu
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
            existingHoaDon.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
            existingHoaDon.setNgayTao(hoaDon.getNgayTao());
            existingHoaDon.setTrangThai(hoaDon.getTrangThai());

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

}
