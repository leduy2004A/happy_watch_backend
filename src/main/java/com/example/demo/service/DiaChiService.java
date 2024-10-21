package com.example.demo.service;

import com.example.demo.model.DiaChi;
import com.example.demo.model.HoaDon;
import com.example.demo.model.NguoiDung;
import com.example.demo.repository.DiaChiRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiaChiService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private DiaChiRepository diaChiRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;
    public List<DiaChi> getDiaChiByNguoiDungId(Long idNguoiDung) {
        return diaChiRepository.findByIdNguoiDung(idNguoiDung);
    }

    public DiaChi updateHoaDonWithNguoiDungAndGetFirstDiaChi(Long idHoaDon, Long idNguoiDung) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon)
                .orElseThrow(() -> new RuntimeException("Hóa đơn không tồn tại"));
        NguoiDung nguoiDung = nguoiDungRepository.findById(idNguoiDung)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        hoaDon.setNguoiDung(nguoiDung);
        hoaDonRepository.save(hoaDon);

        List<DiaChi> diaChis = diaChiRepository.findByIdNguoiDung(idNguoiDung);
        if (!diaChis.isEmpty()) {
            return diaChis.get(0); // Trả về địa chỉ đầu tiên
        } else {
            throw new RuntimeException("Không có địa chỉ nào cho người dùng này.");
        }
    }

    public Optional<DiaChi> getDiaChiByNguoiDungIdAndDiaChiId(Long idNguoiDung, Long idDiaChi) {
        return diaChiRepository.findByIdNguoiDungAndIdDiaChi(idNguoiDung, idDiaChi);
    }
}
