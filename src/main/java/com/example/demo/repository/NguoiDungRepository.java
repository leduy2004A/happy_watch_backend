package com.example.demo.repository;

import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    @Query(value = "select * from nguoi_dung where Username = :username",nativeQuery = true)
    public List<NguoiDung> findNguoiDungByUsername(@Param("username") String username);
    NguoiDung findByUsername(String username);
    @Query("SELECT n FROM NguoiDung n WHERE n.vaiTro.id = 3")
    List<NguoiDung> findAllKhachHang();
}
