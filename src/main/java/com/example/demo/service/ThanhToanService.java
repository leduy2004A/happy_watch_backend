package com.example.demo.service;

import com.example.demo.dto.ThanhToanDTO;
import com.example.demo.model.HoaDon;
import com.example.demo.model.ThanhToan;
import com.example.demo.repository.HoaDonRepository;
import com.example.demo.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThanhToanService {
    @Autowired
    private ThanhToanRepository thanhToanRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;
    public List<ThanhToanDTO> getAllThanhToanWithHoaDonMa() {
        return thanhToanRepository.findAllThanhToanWithHoaDonMa();
    }

    public Optional<ThanhToan> getThanhToanByHoaDonId(Long hoaDonId) {
        // Lấy hóa đơn từ repository
        Optional<HoaDon> hoaDonOpt = hoaDonRepository.findById(hoaDonId);
        if (hoaDonOpt.isPresent()) {
            HoaDon hoaDon = hoaDonOpt.get();
            if (hoaDon.getThanhToan() != null) {
                return Optional.of(hoaDon.getThanhToan());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }
}
