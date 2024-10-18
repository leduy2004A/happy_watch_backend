package com.example.demo.repository;

import com.example.demo.model.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, Long> {
    @Query("SELECT dc FROM DiaChi dc WHERE dc.nguoiDung.id IN :nguoiDungIds")
    List<DiaChi> findDiaChiByNguoiDungIds(@RequestParam("nguoiDungIds") List<Long> nguoiDungIds);

    @Query("SELECT dc FROM DiaChi dc WHERE dc.nguoiDung.id = :nguoiDungId")
    List<DiaChi> findDiaChiByNguoiDungId(@RequestParam("nguoiDungId") Long nguoiDungId);
    @Query("SELECT d FROM DiaChi d WHERE d.nguoiDung.id = :idNguoiDung")
    List<DiaChi> findByIdNguoiDung(@Param("idNguoiDung") Long idNguoiDung);

    @Query("SELECT d FROM DiaChi d WHERE d.nguoiDung.id = :idNguoiDung AND d.id = :idDiaChi")
    Optional<DiaChi> findByIdNguoiDungAndIdDiaChi(@Param("idNguoiDung") Long idNguoiDung, @Param("idDiaChi") Long idDiaChi);
}
