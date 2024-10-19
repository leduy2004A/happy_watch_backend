package com.example.demo.service;

import com.example.demo.dto.ChiTietHoaDonDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ChiTietHoaDonService {

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    private KhuyenMaiHoaDonRepository khuyenMaiHoaDonRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;


    public List<ChiTietHoaDonDTO> getAllChiTietHoaDonWithDetails() {
        List<ChiTietHoaDonDTO> chiTietHoaDonDTOS = chiTietHoaDonRepository.findAllChiTietHoaDon(); // Bạn cần đảm bảo phương thức này trả về List thay vì Page
        List<ChiTietHoaDonDTO> result = new ArrayList<>();

        for (ChiTietHoaDonDTO chiTietHoaDonDTO : chiTietHoaDonDTOS) {
            ChiTietHoaDonDTO dto = new ChiTietHoaDonDTO();
            dto.setChiTietHoaDonId(chiTietHoaDonDTO.getChiTietHoaDonId());
            dto.setHoaDonId(chiTietHoaDonDTO.getHoaDonId());

            BigDecimal slsp = BigDecimal.valueOf(chiTietHoaDonDTO.getSoLuong());
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

        return result; // Trả về danh sách ChiTietHoaDonDTO không phân trang
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

    public Map<String, Object> getChiTietHoaDon(Long idHD) {
        // Gọi repository để lấy danh sách ChiTietHoaDonDTO cho ID hóa đơn
        List<ChiTietHoaDonDTO> chiTietHoaDonDTOList = chiTietHoaDonRepository.findAllChiTietHoaDonDTOByIdHoaDon(idHD);

        // Tạo danh sách để lưu các ChiTietHoaDonDTO đã tính toán
        List<ChiTietHoaDonDTO> result = new ArrayList<>();

        // Tạo biến để tính tổng tiền của toàn bộ chi tiết hóa đơn
        BigDecimal tongTienHoaDon = BigDecimal.ZERO;

        // Duyệt qua danh sách chi tiết hóa đơn
        for (ChiTietHoaDonDTO chiTietHoaDon : chiTietHoaDonDTOList) {
            ChiTietHoaDonDTO dto = new ChiTietHoaDonDTO();

            dto.setChiTietHoaDonId(chiTietHoaDon.getChiTietHoaDonId());
            dto.setHoaDonId(chiTietHoaDon.getHoaDonId());

            // Tính tổng tiền cho mỗi chi tiết hóa đơn
            BigDecimal slsp = BigDecimal.valueOf(chiTietHoaDon.getSoLuong());
            BigDecimal giasp = chiTietHoaDon.getGiaTungSanPham();
            BigDecimal tongTienChiTiet = slsp.multiply(giasp);
            dto.setTongTienChiTietHoaDon(tongTienChiTiet);
            dto.setSanPhamId(chiTietHoaDon.getSanPhamId());
            dto.setMaSanPham(chiTietHoaDon.getMaSanPham());
            dto.setTenSanPham(chiTietHoaDon.getTenSanPham());
            dto.setGiaSanPham(chiTietHoaDon.getGiaSanPham());
            dto.setKhuyenMaiId(chiTietHoaDon.getKhuyenMaiId());
            dto.setChiTietSanPhamId(chiTietHoaDon.getChiTietSanPhamId());
            dto.setMaSanPhamChiTiet(chiTietHoaDon.getMaSanPhamChiTiet());
            dto.setChatLieuDaySanPham(chiTietHoaDon.getChatLieuDaySanPham());
            dto.setChatLieuVoSanPham(chiTietHoaDon.getChatLieuVoSanPham());
            dto.setHinhDangSanPham(chiTietHoaDon.getHinhDangSanPham());
            dto.setLoaiKinhSanPham(chiTietHoaDon.getLoaiKinhSanPham());
            dto.setLoaiMaySanPham(chiTietHoaDon.getLoaiMaySanPham());
            dto.setMauSacSanPham(chiTietHoaDon.getMauSacSanPham());
            dto.setSoLuong(chiTietHoaDon.getSoLuong());
            dto.setGiaTungSanPham(chiTietHoaDon.getGiaTungSanPham());
            // Lấy hình ảnh từ repository và gán vào DTO
            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(chiTietHoaDon.getSanPhamId());
            dto.setHinhAnh(hinhAnhData);

            // Cộng tổng tiền chi tiết vào tổng tiền hóa đơn
            tongTienHoaDon = tongTienHoaDon.add(tongTienChiTiet);

            // Thêm DTO đã tính toán vào danh sách kết quả
            result.add(dto);
        }

        // Lấy danh sách khuyến mãi từ repository
        List<KhuyenMaiHoaDon> khuyenMaiList = khuyenMaiHoaDonRepository.findAll();

        // Tìm mã khuyến mãi tốt nhất
        KhuyenMaiHoaDon khuyenMaiTotNhat = null;
        BigDecimal giaGiamTotNhat = BigDecimal.ZERO;

        // Duyệt qua danh sách khuyến mãi
        for (KhuyenMaiHoaDon km : khuyenMaiList) {
            // Kiểm tra nếu khuyenMaiTuGia không null và tổng tiền đủ điều kiện
            if (km.getKhuyenMaiTuGia() == null || tongTienHoaDon.compareTo(km.getKhuyenMaiTuGia()) >= 0) {
                BigDecimal giaGiam = BigDecimal.ZERO;

                // Tính toán số tiền giảm dựa trên phần trăm hoặc số tiền
                if (km.getPhanTramGiamGia() != null) {
                    giaGiam = tongTienHoaDon.multiply(BigDecimal.valueOf(km.getPhanTramGiamGia() / 100));
                } else if (km.getSoTienGiam() != null) {
                    giaGiam = BigDecimal.valueOf(km.getSoTienGiam());
                }

                // Cập nhật mã khuyến mãi tốt nhất nếu tìm thấy mức giảm tốt hơn
                if (giaGiam.compareTo(giaGiamTotNhat) > 0) {
                    giaGiamTotNhat = giaGiam;
                    khuyenMaiTotNhat = km;
                }
            }
        }

        // Tạo map để trả về danh sách chi tiết hóa đơn và tổng tiền sau khi áp dụng khuyến mãi
        Map<String, Object> response = new HashMap<>();
        response.put("chiTietHoaDons", result);
        response.put("tongTienHoaDon", tongTienHoaDon);

        if (khuyenMaiTotNhat != null) {
            response.put("maKhuyenMaiTotNhat", khuyenMaiTotNhat.getMa());
            response.put("giaGiamTotNhat", giaGiamTotNhat);
            response.put("tongTienSauKhuyenMai", tongTienHoaDon.subtract(giaGiamTotNhat));
        } else {
            response.put("tongTienSauKhuyenMai", tongTienHoaDon);
        }

        return response; // Trả về danh sách chi tiết hóa đơn và tổng tiền hóa đơn sau khuyến mãi
    }



    public Optional<ChiTietHoaDonDTO> addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();

        // Thiết lập thông tin cho chi tiết sản phẩm
        if (chiTietHoaDon.getChiTietSanPham() == null) {
            chiTietHoaDonDTO.setChiTietSanPhamId(null);
        } else {
            // Truy vấn ChiTietSanPham từ cơ sở dữ liệu
            Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findById(chiTietHoaDon.getChiTietSanPham().getId());
            if (!chiTietSanPhamOptional.isPresent()) {
                throw new IllegalArgumentException("Chi tiết sản phẩm không tồn tại.");
            }
            ChiTietSanPham chiTietSanPham = chiTietSanPhamOptional.get();
            chiTietHoaDonDTO.setChiTietSanPhamId(chiTietSanPham.getId());

            // Kiểm tra SanPham có null không
            SanPham sanPham = chiTietSanPham.getSanPham();
            if (sanPham != null) {
                chiTietHoaDonDTO.setSanPhamId(sanPham.getId());
                chiTietHoaDonDTO.setMaSanPham(sanPham.getMa());
                chiTietHoaDonDTO.setTenSanPham(sanPham.getTen());
                chiTietHoaDonDTO.setGiaSanPham(chiTietSanPham.getGia());
                chiTietHoaDonDTO.setKhuyenMaiId(sanPham.getKhuyenMai() != null ? sanPham.getKhuyenMai().getId() : null);
                chiTietHoaDonDTO.setMaSanPhamChiTiet(chiTietSanPham.getMa());
                chiTietHoaDonDTO.setGiaTungSanPham(chiTietHoaDon.getGiaTungSanPham());

                BigDecimal giaTungSanPham = chiTietHoaDon.getGiaTungSanPham();

                BigDecimal tongTienChiTietHoaDon = giaTungSanPham.multiply(new BigDecimal(chiTietHoaDon.getSoLuong()));
                chiTietHoaDonDTO.setTongTienChiTietHoaDon(tongTienChiTietHoaDon);
            } else {
                System.out.println("SanPham is null");
            }

            // Lấy hình ảnh nếu có
            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham != null ? sanPham.getId() : null);
            chiTietHoaDonDTO.setHinhAnh(hinhAnhData);

            // Thiết lập các thuộc tính có thể null trong chi tiết sản phẩm
            chiTietHoaDonDTO.setChatLieuDaySanPham(chiTietSanPham.getChatLieuDay() != null ? chiTietSanPham.getChatLieuDay().getTen() : null);
            chiTietHoaDonDTO.setChatLieuVoSanPham(chiTietSanPham.getChatLieuVo() != null ? chiTietSanPham.getChatLieuVo().getTen() : null);
            chiTietHoaDonDTO.setHinhDangSanPham(chiTietSanPham.getHinhDang() != null ? chiTietSanPham.getHinhDang().getTen() : null);
            chiTietHoaDonDTO.setLoaiKinhSanPham(chiTietSanPham.getLoaiKinh() != null ? chiTietSanPham.getLoaiKinh().getTen() : null);
            chiTietHoaDonDTO.setLoaiMaySanPham(chiTietSanPham.getLoaiMay() != null ? chiTietSanPham.getLoaiMay().getTen() : null);
            chiTietHoaDonDTO.setMauSacSanPham(chiTietSanPham.getMauSac() != null ? chiTietSanPham.getMauSac().getTen() : null);
        }

        // Xử lý thông tin hóa đơn
        if (chiTietHoaDon.getHoaDon() == null) {
            chiTietHoaDonDTO.setHoaDonId(null);
        } else {
            Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(chiTietHoaDon.getHoaDon().getId());
            if (!hoaDonOptional.isPresent()) {
                throw new IllegalArgumentException("Hóa đơn không tồn tại.");
            }
            HoaDon hoaDon = hoaDonOptional.get();
            chiTietHoaDonDTO.setHoaDonId(hoaDon.getId());
        }

        // Kiểm tra tồn tại ChiTietHoaDon với hoaDonId và chiTietSanPhamId
        Optional<ChiTietHoaDon> existingChiTietHoaDon = chiTietHoaDonRepository.findByHoaDonIdAndChiTietSanPhamId(
                chiTietHoaDon.getHoaDon() != null ? chiTietHoaDon.getHoaDon().getId() : null,
                chiTietHoaDon.getChiTietSanPham() != null ? chiTietHoaDon.getChiTietSanPham().getId() : null);

        ChiTietHoaDon chiTietHoaDonNew;

        if (existingChiTietHoaDon.isPresent()) {
            // Tăng số lượng sản phẩm
            chiTietHoaDonNew = existingChiTietHoaDon.get();

            // Kiểm tra số lượng có null không
            Integer currentSoLuong = chiTietHoaDonNew.getSoLuong();
            int newSoLuong = (currentSoLuong != null ? currentSoLuong : 0) + (chiTietHoaDon.getSoLuong() != null ? chiTietHoaDon.getSoLuong() : 0);
            chiTietHoaDonNew.setSoLuong(newSoLuong);
        } else {
            // Thêm mới
            chiTietHoaDonNew = new ChiTietHoaDon();
            chiTietHoaDonNew.setHoaDon(chiTietHoaDon.getHoaDon());
            chiTietHoaDonNew.setChiTietSanPham(chiTietHoaDon.getChiTietSanPham());
            chiTietHoaDonNew.setSoLuong(chiTietHoaDon.getSoLuong() != null ? chiTietHoaDon.getSoLuong() : 0);
            chiTietHoaDonNew.setGiaTungSanPham(chiTietHoaDon.getGiaTungSanPham());
        }

        // Lưu chiTietHoaDon vào cơ sở dữ liệu
        chiTietHoaDonNew = chiTietHoaDonRepository.save(chiTietHoaDonNew);

        // Thiết lập số lượng và ID cho DTO
        chiTietHoaDonDTO.setSoLuong(chiTietHoaDonNew.getSoLuong());
        chiTietHoaDonDTO.setChiTietHoaDonId(chiTietHoaDonNew.getId());
        chiTietHoaDonDTO.setHoaDonId(chiTietHoaDonNew.getHoaDon() != null ? chiTietHoaDonNew.getHoaDon().getId() : null);

        return Optional.of(chiTietHoaDonDTO);
    }













    public Optional<ChiTietHoaDonDTO> updateChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        // Tìm chi tiết hóa đơn theo ID
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepository.findChiTietHoaDonById(chiTietHoaDon.getId());

        if (chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDon existingChiTietHoaDon = chiTietHoaDonOptional.get();

            // Cập nhật thông tin hóa đơn nếu có
            if (chiTietHoaDon.getHoaDon() != null && chiTietHoaDon.getHoaDon().getId() != null) {
                Optional<HoaDon> hoaDonOptional = hoaDonRepository.findById(chiTietHoaDon.getHoaDon().getId());
                if (hoaDonOptional.isPresent()) {
                    existingChiTietHoaDon.setHoaDon(hoaDonOptional.get());
                } else {
                    throw new EntityNotFoundException("Hóa đơn không tồn tại với ID: " + chiTietHoaDon.getHoaDon().getId());
                }
            } else {
                existingChiTietHoaDon.setHoaDon(null);
            }

            // Cập nhật thông tin chi tiết sản phẩm nếu có
            if (chiTietHoaDon.getChiTietSanPham() != null && chiTietHoaDon.getChiTietSanPham().getId() != null) {
                Optional<ChiTietSanPham> chiTietSanPhamOptional = chiTietSanPhamRepository.findById(chiTietHoaDon.getChiTietSanPham().getId());
                if (chiTietSanPhamOptional.isPresent()) {
                    existingChiTietHoaDon.setChiTietSanPham(chiTietSanPhamOptional.get());
                } else {
                    throw new EntityNotFoundException("Chi tiết sản phẩm không tồn tại với ID: " + chiTietHoaDon.getChiTietSanPham().getId());
                }
            } else {
                existingChiTietHoaDon.setChiTietSanPham(null);
            }

            // Cập nhật các thuộc tính khác
            existingChiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
            existingChiTietHoaDon.setGiaTungSanPham(chiTietHoaDon.getGiaTungSanPham());

            // Lưu lại hóa đơn đã cập nhật
            chiTietHoaDonRepository.save(existingChiTietHoaDon);

            // Tạo DTO cho chi tiết hóa đơn đã cập nhật
            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            SanPham sanPham = existingChiTietHoaDon.getChiTietSanPham().getSanPham(); // Giả sử có thể lấy SanPham từ ChiTietSanPham

            // Tính tổng tiền chi tiết hóa đơn
            BigDecimal tongTienChiTietHoaDon = chiTietHoaDon.getGiaTungSanPham()
                    .multiply(new BigDecimal(existingChiTietHoaDon.getSoLuong()));

            // Thiết lập các thông tin cho DTO
            chiTietHoaDonDTO.setChiTietHoaDonId(existingChiTietHoaDon.getId());
            chiTietHoaDonDTO.setHoaDonId(existingChiTietHoaDon.getHoaDon().getId());
            chiTietHoaDonDTO.setTongTienChiTietHoaDon(tongTienChiTietHoaDon);
            chiTietHoaDonDTO.setSanPhamId(sanPham.getId());
            chiTietHoaDonDTO.setMaSanPham(sanPham.getMa());
            chiTietHoaDonDTO.setTenSanPham(sanPham.getTen());

            chiTietHoaDonDTO.setKhuyenMaiId(sanPham.getKhuyenMai().getId());

            // Thiết lập mã sản phẩm chi tiết
            chiTietHoaDonDTO.setMaSanPhamChiTiet(existingChiTietHoaDon.getChiTietSanPham().getMa());
            chiTietHoaDonDTO.setGiaSanPham(existingChiTietHoaDon.getChiTietSanPham().getGia());
            // Thiết lập các thuộc tính khác từ chiTietSanPham
            chiTietHoaDonDTO.setChiTietSanPhamId(existingChiTietHoaDon.getChiTietSanPham().getId());
            chiTietHoaDonDTO.setChatLieuDaySanPham(existingChiTietHoaDon.getChiTietSanPham().getChatLieuDay().getTen());
            chiTietHoaDonDTO.setChatLieuVoSanPham(existingChiTietHoaDon.getChiTietSanPham().getChatLieuVo().getTen());
            chiTietHoaDonDTO.setHinhDangSanPham(existingChiTietHoaDon.getChiTietSanPham().getHinhDang().getTen());
            chiTietHoaDonDTO.setLoaiKinhSanPham(existingChiTietHoaDon.getChiTietSanPham().getLoaiKinh().getTen());
            chiTietHoaDonDTO.setLoaiMaySanPham(existingChiTietHoaDon.getChiTietSanPham().getLoaiMay().getTen());
            chiTietHoaDonDTO.setMauSacSanPham(existingChiTietHoaDon.getChiTietSanPham().getMauSac().getTen());
            chiTietHoaDonDTO.setGiaTungSanPham(existingChiTietHoaDon.getGiaTungSanPham());
            chiTietHoaDonDTO.setSoLuong(existingChiTietHoaDon.getSoLuong());

            // Lấy danh sách hình ảnh
            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());
            chiTietHoaDonDTO.setHinhAnh(hinhAnhData);

            return Optional.of(chiTietHoaDonDTO);
        }

        return Optional.empty();
    }

    public Optional<ChiTietHoaDonDTO> congSoLuongSanPhamHoaDon(Long idCTHD) {
        // Tìm chi tiết hóa đơn theo ID
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepository.findChiTietHoaDonById(idCTHD);

        if (chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDon existingChiTietHoaDon = chiTietHoaDonOptional.get();

            // Cập nhật số lượng, cộng thêm 1
            existingChiTietHoaDon.setSoLuong(existingChiTietHoaDon.getSoLuong() + 1);

            // Lưu lại hóa đơn đã cập nhật
            chiTietHoaDonRepository.save(existingChiTietHoaDon);

            // Tạo DTO cho chi tiết hóa đơn đã cập nhật
            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            SanPham sanPham = existingChiTietHoaDon.getChiTietSanPham().getSanPham(); // Giả sử có thể lấy SanPham từ ChiTietSanPham

            // Tính tổng tiền chi tiết hóa đơn
            BigDecimal tongTienChiTietHoaDon = existingChiTietHoaDon.getGiaTungSanPham()
                    .multiply(new BigDecimal(existingChiTietHoaDon.getSoLuong()));

            // Thiết lập các thông tin cho DTO
            chiTietHoaDonDTO.setChiTietHoaDonId(existingChiTietHoaDon.getId());
            chiTietHoaDonDTO.setHoaDonId(existingChiTietHoaDon.getHoaDon().getId());
            chiTietHoaDonDTO.setTongTienChiTietHoaDon(tongTienChiTietHoaDon);
            chiTietHoaDonDTO.setSanPhamId(sanPham.getId());
            chiTietHoaDonDTO.setMaSanPham(sanPham.getMa());
            chiTietHoaDonDTO.setTenSanPham(sanPham.getTen());

            chiTietHoaDonDTO.setKhuyenMaiId(sanPham.getKhuyenMai() != null ? sanPham.getKhuyenMai().getId() : null);

            // Thiết lập mã sản phẩm chi tiết
            chiTietHoaDonDTO.setMaSanPhamChiTiet(existingChiTietHoaDon.getChiTietSanPham().getMa());
            chiTietHoaDonDTO.setGiaSanPham(existingChiTietHoaDon.getChiTietSanPham().getGia());
            // Thiết lập các thuộc tính khác từ chiTietSanPham
            chiTietHoaDonDTO.setChiTietSanPhamId(existingChiTietHoaDon.getChiTietSanPham().getId());
            chiTietHoaDonDTO.setChatLieuDaySanPham(existingChiTietHoaDon.getChiTietSanPham().getChatLieuDay() != null ? existingChiTietHoaDon.getChiTietSanPham().getChatLieuDay().getTen() : null);
            chiTietHoaDonDTO.setChatLieuVoSanPham(existingChiTietHoaDon.getChiTietSanPham().getChatLieuVo() != null ? existingChiTietHoaDon.getChiTietSanPham().getChatLieuVo().getTen() : null);
            chiTietHoaDonDTO.setHinhDangSanPham(existingChiTietHoaDon.getChiTietSanPham().getHinhDang() != null ? existingChiTietHoaDon.getChiTietSanPham().getHinhDang().getTen() : null);
            chiTietHoaDonDTO.setLoaiKinhSanPham(existingChiTietHoaDon.getChiTietSanPham().getLoaiKinh() != null ? existingChiTietHoaDon.getChiTietSanPham().getLoaiKinh().getTen() : null);
            chiTietHoaDonDTO.setLoaiMaySanPham(existingChiTietHoaDon.getChiTietSanPham().getLoaiMay() != null ? existingChiTietHoaDon.getChiTietSanPham().getLoaiMay().getTen() : null);
            chiTietHoaDonDTO.setMauSacSanPham(existingChiTietHoaDon.getChiTietSanPham().getMauSac() != null ? existingChiTietHoaDon.getChiTietSanPham().getMauSac().getTen() : null);
            chiTietHoaDonDTO.setGiaTungSanPham(existingChiTietHoaDon.getGiaTungSanPham());
            chiTietHoaDonDTO.setSoLuong(existingChiTietHoaDon.getSoLuong());

            // Lấy danh sách hình ảnh
            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());
            chiTietHoaDonDTO.setHinhAnh(hinhAnhData);

            return Optional.of(chiTietHoaDonDTO);
        }

        return Optional.empty();
    }

    public Optional<ChiTietHoaDonDTO> truSoLuongSanPhamHoaDon(Long idCTHD) {
        // Tìm chi tiết hóa đơn theo ID
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepository.findChiTietHoaDonById(idCTHD);

        if (chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDon existingChiTietHoaDon = chiTietHoaDonOptional.get();

            // Cập nhật số lượng, cộng thêm 1
            existingChiTietHoaDon.setSoLuong(existingChiTietHoaDon.getSoLuong() - 1);

            // Lưu lại hóa đơn đã cập nhật
            chiTietHoaDonRepository.save(existingChiTietHoaDon);

            // Tạo DTO cho chi tiết hóa đơn đã cập nhật
            ChiTietHoaDonDTO chiTietHoaDonDTO = new ChiTietHoaDonDTO();
            SanPham sanPham = existingChiTietHoaDon.getChiTietSanPham().getSanPham(); // Giả sử có thể lấy SanPham từ ChiTietSanPham

            // Tính tổng tiền chi tiết hóa đơn
            BigDecimal tongTienChiTietHoaDon = existingChiTietHoaDon.getGiaTungSanPham()
                    .multiply(new BigDecimal(existingChiTietHoaDon.getSoLuong()));

            // Thiết lập các thông tin cho DTO
            chiTietHoaDonDTO.setChiTietHoaDonId(existingChiTietHoaDon.getId());
            chiTietHoaDonDTO.setHoaDonId(existingChiTietHoaDon.getHoaDon().getId());
            chiTietHoaDonDTO.setTongTienChiTietHoaDon(tongTienChiTietHoaDon);
            chiTietHoaDonDTO.setSanPhamId(sanPham.getId());
            chiTietHoaDonDTO.setMaSanPham(sanPham.getMa());
            chiTietHoaDonDTO.setTenSanPham(sanPham.getTen());

            chiTietHoaDonDTO.setKhuyenMaiId(sanPham.getKhuyenMai() != null ? sanPham.getKhuyenMai().getId() : null);

            // Thiết lập mã sản phẩm chi tiết
            chiTietHoaDonDTO.setMaSanPhamChiTiet(existingChiTietHoaDon.getChiTietSanPham().getMa());
            chiTietHoaDonDTO.setGiaSanPham(existingChiTietHoaDon.getChiTietSanPham().getGia());
            // Thiết lập các thuộc tính khác từ chiTietSanPham
            chiTietHoaDonDTO.setChiTietSanPhamId(existingChiTietHoaDon.getChiTietSanPham().getId());
            chiTietHoaDonDTO.setChatLieuDaySanPham(existingChiTietHoaDon.getChiTietSanPham().getChatLieuDay() != null ? existingChiTietHoaDon.getChiTietSanPham().getChatLieuDay().getTen() : null);
            chiTietHoaDonDTO.setChatLieuVoSanPham(existingChiTietHoaDon.getChiTietSanPham().getChatLieuVo() != null ? existingChiTietHoaDon.getChiTietSanPham().getChatLieuVo().getTen() : null);
            chiTietHoaDonDTO.setHinhDangSanPham(existingChiTietHoaDon.getChiTietSanPham().getHinhDang() != null ? existingChiTietHoaDon.getChiTietSanPham().getHinhDang().getTen() : null);
            chiTietHoaDonDTO.setLoaiKinhSanPham(existingChiTietHoaDon.getChiTietSanPham().getLoaiKinh() != null ? existingChiTietHoaDon.getChiTietSanPham().getLoaiKinh().getTen() : null);
            chiTietHoaDonDTO.setLoaiMaySanPham(existingChiTietHoaDon.getChiTietSanPham().getLoaiMay() != null ? existingChiTietHoaDon.getChiTietSanPham().getLoaiMay().getTen() : null);
            chiTietHoaDonDTO.setMauSacSanPham(existingChiTietHoaDon.getChiTietSanPham().getMauSac() != null ? existingChiTietHoaDon.getChiTietSanPham().getMauSac().getTen() : null);
            chiTietHoaDonDTO.setGiaTungSanPham(existingChiTietHoaDon.getGiaTungSanPham());
            chiTietHoaDonDTO.setSoLuong(existingChiTietHoaDon.getSoLuong());

            // Lấy danh sách hình ảnh
            List<String> hinhAnhData = hinhAnhRepository.getHinhAnhsByIdSanPham(sanPham.getId());
            chiTietHoaDonDTO.setHinhAnh(hinhAnhData);

            return Optional.of(chiTietHoaDonDTO);
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
