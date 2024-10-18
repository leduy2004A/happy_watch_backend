package com.example.demo.repository;

import com.example.demo.model.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, Long> {
    @Query("SELECT d FROM DiaChi d WHERE d.nguoiDung.id = :idNguoiDung")
    List<DiaChi> findByIdNguoiDung(@Param("idNguoiDung") Long idNguoiDung);

    @Query("SELECT d FROM DiaChi d WHERE d.nguoiDung.id = :idNguoiDung AND d.id = :idDiaChi")
    Optional<DiaChi> findByIdNguoiDungAndIdDiaChi(@Param("idNguoiDung") Long idNguoiDung, @Param("idDiaChi") Long idDiaChi);
}
