package com.example.demo.repository;

import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    @Query("SELECT n FROM NguoiDung n WHERE n.vaiTro.id = 3")
    List<NguoiDung> findAllKhachHang();
}
