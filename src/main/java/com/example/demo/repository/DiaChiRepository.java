package com.example.demo.repository;

import com.example.demo.model.DiaChi;
import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, Long> {
    @Query("SELECT dc FROM DiaChi dc WHERE dc.nguoiDung.id IN :nguoiDungIds")
    List<DiaChi> findDiaChiByNguoiDungIds(@RequestParam("nguoiDungIds") List<Long> nguoiDungIds);
}
