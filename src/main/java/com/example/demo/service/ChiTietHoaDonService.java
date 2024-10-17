package com.example.demo.service;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.model.ChiTietHoaDon;
import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.HinhAnh;
import com.example.demo.model.HoaDon;
import com.example.demo.repository.ChiTietHoaDonRepository;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.repository.HoaDonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChiTietHoaDonService {

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;


    public Page<ChiTietHoaDonDTO> getAllChiTietHoaDonWithDetails(Pageable pageable) {

        Page<ChiTietHoaDonDTO> chiTietHoaDonDTOS = chiTietHoaDonRepository.findAllChiTietHoaDon(pageable);
        List<ChiTietHoaDonDTO> result = new ArrayList<>();

        for (ChiTietHoaDonDTO chiTietHoaDonDTO : chiTietHoaDonDTOS.getContent()) {

            ChiTietHoaDonDTO dto = new ChiTietHoaDonDTO();
            dto.setChiTietHoaDonId(chiTietHoaDonDTO.getChiTietHoaDonId());
            dto.setHoaDonId(chiTietHoaDonDTO.getHoaDonId());

            BigDecimal slsp = BigDecimal.valueOf(chiTietHoaDonDTO.getSoLuong()) ;

            BigDecimal giasp = chiTietHoaDonDTO.getGiaSanPham();

            BigDecimal giaa = slsp.multiply(giasp);

            dto.setTongTienChiTietHoaDon(giaa);
            dto.setSanPhamId(chiTietHoaDonDTO.getSanPhamId());
            dto.setMaSanPham(chiTietHoaDonDTO.getMaSanPham());
            dto.setTenSanPham(chiTietHoaDonDTO.getTenSanPham());
            dto.setGiaSanPham(chiTietHoaDonDTO.getGiaSanPham());
            dto.setKhuyenMaiId(chiTietHoaDonDTO.getKhuyenMaiId());
            dto.setChiTietSanPhamId(chiTietHoaDonDTO.getChiTietSanPhamId());
            dto.setMaSanPhamChiTiet(chiTietHoaDonDTO.getMaSanPham());
            dto.setChatLieuDaySanPham(chiTietHoaDonDTO.getChatLieuDaySanPham());
            dto.setChatLieuVoSanPham(chiTietHoaDonDTO.getChatLieuVoSanPham());
            dto.setHinhDangSanPham(chiTietHoaDonDTO.getHinhDangSanPham());
            dto.setLoaiKinhSanPham(chiTietHoaDonDTO.getLoaiKinhSanPham());
            dto.setLoaiMaySanPham(chiTietHoaDonDTO.getLoaiMaySanPham());
            dto.setMauSacSanPham(chiTietHoaDonDTO.getMauSacSanPham());
            dto.setSoLuong(chiTietHoaDonDTO.getSoLuong());
            dto.setGiaTungSanPham(chiTietHoaDonDTO.getGiaTungSanPham());

            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(chiTietHoaDonDTO.getSanPhamId());

            dto.setHinhAnh(hinhAnhData);

            result.add(dto);
        }
        return new PageImpl<>(result, pageable, chiTietHoaDonDTOS.getTotalElements());
    }

    public Optional<ChiTietHoaDonDTO> getChiTietHoaDonById(Long id) {
        Optional<ChiTietHoaDonDTO> chiTietHoaDonOptional = chiTietHoaDonRepository.findChiTietHoaDonDTOById(id);
        if (chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDonDTO dto = new ChiTietHoaDonDTO();
            dto.setChiTietHoaDonId(chiTietHoaDonOptional.get().getChiTietHoaDonId());
            dto.setHoaDonId(chiTietHoaDonOptional.get().getHoaDonId());

            BigDecimal slsp = BigDecimal.valueOf(chiTietHoaDonOptional.get().getSoLuong()) ;

            BigDecimal giasp = chiTietHoaDonOptional.get().getGiaSanPham();

            BigDecimal giaa = slsp.multiply(giasp);

            dto.setTongTienChiTietHoaDon(giaa);
            dto.setSanPhamId(chiTietHoaDonOptional.get().getSanPhamId());
            dto.setMaSanPham(chiTietHoaDonOptional.get().getMaSanPham());
            dto.setTenSanPham(chiTietHoaDonOptional.get().getTenSanPham());
            dto.setGiaSanPham(chiTietHoaDonOptional.get().getGiaSanPham());
            dto.setKhuyenMaiId(chiTietHoaDonOptional.get().getKhuyenMaiId());
            dto.setChiTietSanPhamId(chiTietHoaDonOptional.get().getChiTietSanPhamId());
            dto.setMaSanPhamChiTiet(chiTietHoaDonOptional.get().getMaSanPhamChiTiet());
            dto.setChatLieuDaySanPham(chiTietHoaDonOptional.get().getChatLieuDaySanPham());
            dto.setChatLieuVoSanPham(chiTietHoaDonOptional.get().getChatLieuVoSanPham());
            dto.setHinhDangSanPham(chiTietHoaDonOptional.get().getHinhDangSanPham());
            dto.setLoaiKinhSanPham(chiTietHoaDonOptional.get().getLoaiKinhSanPham());
            dto.setLoaiMaySanPham(chiTietHoaDonOptional.get().getLoaiMaySanPham());
            dto.setMauSacSanPham(chiTietHoaDonOptional.get().getMauSacSanPham());
            dto.setSoLuong(chiTietHoaDonOptional.get().getSoLuong());
            dto.setGiaTungSanPham(chiTietHoaDonOptional.get().getGiaTungSanPham());
            
            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(chiTietHoaDonOptional.get().getSanPhamId());

            dto.setHinhAnh(hinhAnhData);

            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<ChiTietHoaDon> addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        ChiTietHoaDon chiTietHoaDonNew = new ChiTietHoaDon();
        chiTietHoaDonNew.setHoaDon(chiTietHoaDon.getHoaDon());
        chiTietHoaDonNew.setChiTietSanPham(chiTietHoaDon.getChiTietSanPham());
        chiTietHoaDonNew.setSoLuong(chiTietHoaDon.getSoLuong());
        chiTietHoaDonNew.setGiaTungSanPham(chiTietHoaDon.getGiaTungSanPham());
        // Lưu vào cơ sở dữ liệu
        chiTietHoaDonRepository.save(chiTietHoaDonNew);

        return Optional.of(chiTietHoaDonNew);
    }



    public Optional<ChiTietHoaDon> updateChiTietHoaDon(ChiTietHoaDon chiTietHoaDon, BigDecimal giaTungSanPham) {
        // Tìm hóa đơn theo mã
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepository.findChiTietHoaDonById(chiTietHoaDon.getId());

        if (chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDon existingChiTietHoaDon = chiTietHoaDonOptional.get();

            // Nếu bạn có ID của NguoiDung, hãy lấy nó từ repository
            if (chiTietHoaDon.getHoaDon() != null && chiTietHoaDon.getHoaDon().getId() != null) {
                Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(chiTietHoaDon.getHoaDon().getId());
                if (hoaDonOptional.isPresent()) {
                    existingChiTietHoaDon.setHoaDon(hoaDonOptional.get());
                } else {
                    // Xử lý trường hợp NguoiDung không tồn tại
                    throw new EntityNotFoundException("NguoiDung not found with id: " + chiTietHoaDon.getHoaDon().getId());
                }
            } else {
                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
                existingChiTietHoaDon.setHoaDon(null);
            }

            if (chiTietHoaDon.getChiTietSanPham() != null && chiTietHoaDon.getChiTietSanPham().getId() != null) {
                Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findById(chiTietHoaDon.getChiTietSanPham().getId());
                if (chiTietSanPhamOptional.isPresent()) {
                    existingChiTietHoaDon.setChiTietSanPham(chiTietSanPhamOptional.get());
                } else {
                    // Xử lý trường hợp NguoiDung không tồn tại
                    throw new EntityNotFoundException("NguoiDung not found with id: " + chiTietHoaDon.getChiTietSanPham().getId());
                }
            } else {
                // Xử lý trường hợp NguoiDung không được cung cấp hoặc không có ID
                existingChiTietHoaDon.setChiTietSanPham(null);
            }

            existingChiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());

            existingChiTietHoaDon.setGiaTungSanPham(giaTungSanPham);

            // Lưu lại hóa đơn đã cập nhật
            chiTietHoaDonRepository.save(existingChiTietHoaDon);

            return Optional.of(existingChiTietHoaDon);
        }

        return Optional.empty();
    }

    public Optional<ChiTietHoaDon> deleteChiTietHoaDon(Long idChiTietHoaDon) {
        // Tìm hóa đơn theo mã
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepository.findChiTietHoaDonById(idChiTietHoaDon);

        if (chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDon existingChiTietHoaDon = chiTietHoaDonOptional.get();
            // Lưu lại hóa đơn đã cập nhật
            chiTietHoaDonRepository.delete(existingChiTietHoaDon);

            return Optional.of(existingChiTietHoaDon);
        }

        return Optional.empty();
    }

}
