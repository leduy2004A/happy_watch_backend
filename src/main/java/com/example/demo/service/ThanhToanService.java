package com.example.demo.service;

import com.example.demo.dto.ThanhToanDTO;
import com.example.demo.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanhToanService {
    @Autowired
    private ThanhToanRepository thanhToanRepository;

    public List<ThanhToanDTO> getAllThanhToanWithHoaDonMa() {
        return thanhToanRepository.findAllThanhToanWithHoaDonMa();
    }
}
