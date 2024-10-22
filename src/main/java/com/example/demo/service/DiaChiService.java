package com.example.demo.service;

import com.example.demo.appException.AppException;
import com.example.demo.model.DiaChi;
import com.example.demo.model.HoaDon;
import com.example.demo.model.NguoiDung;
import com.example.demo.repository.DiaChiRepository;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new AppException(404, "Hóa đơn không tồn tại"));
        NguoiDung nguoiDung = nguoiDungRepository.findById(idNguoiDung)
                .orElseThrow(() -> new AppException(404, "Người dùng không tồn tại"));

        hoaDon.setNguoiDung(nguoiDung);
        hoaDonRepository.save(hoaDon);

        List<DiaChi> diaChis = diaChiRepository.findByIdNguoiDung(idNguoiDung);
        if (diaChis.isEmpty()) {
            throw new AppException(404, "Người dùng này không có địa chỉ nào.");
        }
        return diaChis.get(0);
    }

    public Optional<DiaChi> getDiaChiByNguoiDungIdAndDiaChiId(Long idNguoiDung, Long idDiaChi) {
        return diaChiRepository.findByIdNguoiDungAndIdDiaChi(idNguoiDung, idDiaChi);
    }
}
