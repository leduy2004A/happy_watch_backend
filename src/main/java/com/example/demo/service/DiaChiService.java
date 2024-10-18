package com.example.demo.service;

import com.example.demo.model.DiaChi;
import com.example.demo.model.NguoiDung;
import com.example.demo.repository.DiaChiRepository;
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


    public List<DiaChi> getDiaChiByNguoiDungId(Long idNguoiDung) {
        return diaChiRepository.findByIdNguoiDung(idNguoiDung);
    }

    public Optional<DiaChi> getFirstDiaChiByNguoiDungId(Long idNguoiDung) {
        List<DiaChi> diaChis = diaChiRepository.findByIdNguoiDung(idNguoiDung);
        if (!diaChis.isEmpty()) {
            return Optional.of(diaChis.get(0));
        }
        return Optional.empty();
    }

    public Optional<DiaChi> getDiaChiByNguoiDungIdAndDiaChiId(Long idNguoiDung, Long idDiaChi) {
        return diaChiRepository.findByIdNguoiDungAndIdDiaChi(idNguoiDung, idDiaChi);
    }
}
