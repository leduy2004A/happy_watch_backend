package com.example.demo.repository;

import com.example.demo.dto.ThanhToanDTO;
import com.example.demo.model.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan,Long> {
    @Query("SELECT new com.example.demo.dto.ThanhToanDTO(t.ma, t.phuongThuc, t.soTien, h.ma) " +
            "FROM ThanhToan t " +
            "LEFT JOIN HoaDon h ON t.id = h.thanhToan.id")
    List<ThanhToanDTO> findAllThanhToanWithHoaDonMa();
}
