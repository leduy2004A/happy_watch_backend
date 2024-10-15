package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    Page<HoaDon> findAll(Pageable pageable);

    @Query("SELECT hd FROM HoaDon hd WHERE hd.id = :hoaDonId")
    List<HoaDon> findHoaDonById(@Param("hoaDonId") Long hoaDonId);

    Optional<HoaDon> findByMa(String ma);
}
