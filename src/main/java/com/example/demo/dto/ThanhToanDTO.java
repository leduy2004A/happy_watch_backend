package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThanhToanDTO {
    private String maThanhToan;
    private String phuongThuc;
    private BigDecimal soTien;
    private String maHoaDon;
}
