package com.example.demo.service;

import com.example.demo.model.DiaChi;
import com.example.demo.model.NguoiDung;
import com.example.demo.repository.DiaChiRepository;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaChiService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private DiaChiRepository diaChiRepository;
    public List<DiaChi> getDiaChiKhachHang() {
        List<NguoiDung> khachHang = nguoiDungRepository.findAllKhachHang();
        List<Long> nguoiDungIds = khachHang.stream()
                .map(NguoiDung::getId)
                .collect(Collectors.toList());
        return diaChiRepository.findDiaChiByNguoiDungIds(nguoiDungIds);
    }
}
