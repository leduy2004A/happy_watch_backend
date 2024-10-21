package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SanPhamChiTietDTO;
import com.example.demo.model.ChiTietSanPham;
import com.example.demo.model.KhuyenMai;
import com.example.demo.model.SanPham;
import com.example.demo.repository.ChiTietSanPhamRepository;
import com.example.demo.repository.HinhAnhRepository;
import com.example.demo.repository.KhuyenMaiRepository;
import com.example.demo.repository.SanPhamRepository;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;


    public SanPham createSanPham(SanPham sanPham) {
        sanPham.setCreatedAt(LocalDate.now());
        sanPham.setUpdatedAt(LocalDate.now());
        return sanPhamRepository.save(sanPham);
    }
    public BigDecimal tinhGiaSauGiam(BigDecimal giaGoc, KhuyenMai khuyenMai) {
        BigDecimal giaSauGiam = giaGoc;
        if (khuyenMai != null) {
            if ("phan tram".equalsIgnoreCase(khuyenMai.getLoaiKhuyenMai())) {
                BigDecimal phanTramGiamGia = BigDecimal.valueOf(khuyenMai.getPhanTramGiamGia());
                BigDecimal giamGia = giaGoc.multiply(phanTramGiamGia).divide(BigDecimal.valueOf(100));
                giaSauGiam = giaGoc.subtract(giamGia);
            } else if ("so tien".equalsIgnoreCase(khuyenMai.getLoaiKhuyenMai())) {
                BigDecimal soTienGiam = khuyenMai.getSoTienGiam();
                giaSauGiam = giaGoc.subtract(soTienGiam);
            }
        }

        return giaSauGiam.max(BigDecimal.ZERO);
    }

    public SanPham updateSanPhamByMa(String ma, SanPham sanPhamDetails) {
        SanPham existingSanPham = sanPhamRepository.findByMa(ma);
        if (existingSanPham != null) {
            existingSanPham.setTen(sanPhamDetails.getTen());
            existingSanPham.setMoTa(sanPhamDetails.getMoTa());
            existingSanPham.setUpdatedAt(LocalDate.now());
            return sanPhamRepository.save(existingSanPham);
        }
        return null;
    }

    public List<SanPhamChiTietDTO> getAllSanPham() {
        List<SanPham> sanPhams = sanPhamRepository.findAll();
        List<SanPhamChiTietDTO> result = new ArrayList<>();

        for (SanPham sanPham : sanPhams) {
            List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamRepository.findBySanPham_Id(sanPham.getId());
            for (ChiTietSanPham chiTiet : chiTietSanPhams) {
                SanPhamChiTietDTO dto = createSanPhamChiTietDTO(sanPham, chiTiet);
                result.add(dto);
            }
        }
        return result;
    }

    public Page<SanPhamChiTietDTO> getAllSanPhamWithDetails(Pageable pageable) {
        Page<SanPham> sanPhams = sanPhamRepository.findAll(pageable);
        List<SanPhamChiTietDTO> result = new ArrayList<>();

        for (SanPham sanPham : sanPhams.getContent()) {
            List<ChiTietSanPham> chiTietSanPhams = chiTietSanPhamRepository.findBySanPham_Id(sanPham.getId());
            for (ChiTietSanPham chiTiet : chiTietSanPhams) {
                SanPhamChiTietDTO dto = createSanPhamChiTietDTO(sanPham, chiTiet);
                result.add(dto);
            }
        }
        return new PageImpl<>(result, pageable, sanPhams.getTotalElements());
    }
    public Optional<SanPhamChiTietDTO> getSanPhamByMaChiTiet(String maChiTiet) {
        Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findByMa(maChiTiet);
        if (chiTietSanPhamOptional.isPresent()) {
            ChiTietSanPham chiTietSanPham = chiTietSanPhamOptional.get();
            SanPham sanPham = chiTietSanPham.getSanPham();
            List<String> hinhAnhs = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());

            SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
            populateSanPhamChiTietDTO(dto, sanPham, chiTietSanPham, hinhAnhs);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    private SanPhamChiTietDTO createSanPhamChiTietDTO(SanPham sanPham, ChiTietSanPham chiTietSanPham) {
        SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
        List<String> hinhAnhs = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());
        dto.setSanPhamId(sanPham.getId());
        dto.setMaSanPham(sanPham.getMa());
        dto.setHinhAnh(hinhAnhs);
        dto.setTenSanPham(sanPham.getTen());
        dto.setGia(chiTietSanPham.getGia());
        KhuyenMai khuyenMai = sanPham.getKhuyenMai();
        System.out.println("KhuyenMai: " + khuyenMai); // Log kiểm tra khuyến mãi
        System.out.println("Gia goc: " + dto.getGia());
        if (khuyenMai != null) {
            BigDecimal giaSauGiam = tinhGiaSauGiam(dto.getGia(), khuyenMai);
            System.out.println("Gia sau giam: " + giaSauGiam); // Kiểm tra giá sau giảm
            dto.setGiaSauGiam(giaSauGiam);
        } else {
            dto.setGiaSauGiam(dto.getGia());
        }

        dto.setTenMauSac(chiTietSanPham.getMauSac().getTen());
        dto.setLoaiMay(chiTietSanPham.getLoaiMay().getTen());
        dto.setChatLieuVo(chiTietSanPham.getChatLieuVo().getTen());
        dto.setChatLieuDay(chiTietSanPham.getChatLieuDay().getTen());
        dto.setKichThuoc(chiTietSanPham.getKichThuoc());
        dto.setChongNuoc(chiTietSanPham.getChongNuoc());
        dto.setLoaiKinh(chiTietSanPham.getLoaiKinh().getTen());
        dto.setHinhDang(chiTietSanPham.getHinhDang().getTen());
        dto.setGioiTinh(sanPham.getGioiTinh().getTen());
        dto.setXuatXu(chiTietSanPham.getXuatXu());
        dto.setBaoHanh(chiTietSanPham.getBaoHanh());
        dto.setCanNang(chiTietSanPham.getCanNang());
        dto.setChiTietSanPhamId(chiTietSanPham.getId());
        return dto;
    }


    private void populateSanPhamChiTietDTO(SanPhamChiTietDTO dto, SanPham sanPham, ChiTietSanPham chiTietSanPham, List<String> hinhAnhs) {
        dto.setSanPhamId(sanPham.getId());
        dto.setMaSanPham(sanPham.getMa());
        dto.setHinhAnh(hinhAnhs);
        dto.setTenSanPham(sanPham.getTen());
        KhuyenMai khuyenMai = sanPham.getKhuyenMai();
        dto.setGia(chiTietSanPham.getGia());
        if (khuyenMai != null) {
            BigDecimal giaSauGiam = tinhGiaSauGiam(dto.getGia(), khuyenMai);
            dto.setGiaSauGiam(giaSauGiam);
        } else {
            dto.setGiaSauGiam(dto.getGia());
        }
        dto.setTenMauSac(chiTietSanPham.getMauSac().getTen());
        dto.setLoaiMay(chiTietSanPham.getLoaiMay().getTen());
        dto.setChatLieuVo(chiTietSanPham.getChatLieuVo().getTen());
        dto.setChatLieuDay(chiTietSanPham.getChatLieuDay().getTen());
        dto.setKichThuoc(chiTietSanPham.getKichThuoc());
        dto.setChongNuoc(chiTietSanPham.getChongNuoc());
        dto.setLoaiKinh(chiTietSanPham.getLoaiKinh().getTen());
        dto.setHinhDang(chiTietSanPham.getHinhDang().getTen());
        dto.setGioiTinh(sanPham.getGioiTinh().getTen());
        dto.setXuatXu(chiTietSanPham.getXuatXu());
        dto.setBaoHanh(chiTietSanPham.getBaoHanh());
    }
}
