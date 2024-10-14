package com.example.demo.service;

import com.example.demo.dto.HoaDonDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.model.NguoiDung;
import com.example.demo.model.VaiTro;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.NguoiDungRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    public Page<HoaDonDTO> getAllHoaDonWithDetails(Pageable pageable) {

        Page<HoaDon> hoaDons = hoaDonRepository.findAll(pageable);
        List<HoaDonDTO> result = new ArrayList<>();

        for (HoaDon hoaDon : hoaDons.getContent()) {
            NguoiDung nguoiDung = hoaDon.getNguoiDung();
            VaiTro vaiTro = nguoiDung.getVaiTro();

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
            dto.setNguoiDungDiaChi(nguoiDung.getDiaChi());
            dto.setNguoiDungDienThoai(nguoiDung.getDienThoai());

            dto.setVaiTroId(vaiTro.getId());
            dto.setVaiTroTen(vaiTro.getTenVaiTro());

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
            dto.setNguoiDungDiaChi(nguoiDung.getDiaChi());
            dto.setNguoiDungDienThoai(nguoiDung.getDienThoai());

            dto.setVaiTroId(vaiTro.getId());
            dto.setVaiTroTen(vaiTro.getTenVaiTro());
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<HoaDon> addHoaDon(HoaDon hoaDon) {
        HoaDon hoaDonNew = new HoaDon();
        hoaDonNew.setMa(hoaDon.getMa());
        hoaDonNew.setTenNguoiNhan(hoaDon.getTenNguoiNhan());
        hoaDonNew.setGia(hoaDon.getGia());
        hoaDonNew.setDiaChiGiaoHang(hoaDon.getDiaChiGiaoHang());
        hoaDonNew.setNgayTao(hoaDon.getNgayTao());
        hoaDonNew.setTrangThai(hoaDon.getTrangThai());

        // Lưu vào cơ sở dữ liệu
        hoaDonRepository.save(hoaDonNew);

        return Optional.of(hoaDon);
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
