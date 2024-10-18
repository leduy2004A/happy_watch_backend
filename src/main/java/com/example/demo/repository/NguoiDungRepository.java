package com.example.demo.repository;

import com.example.demo.dto.NguoiDungDTO;
import com.example.demo.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
    @Query("SELECT new com.example.demo.dto.NguoiDungDTO(nd.id, nd.ma, nd.avatar, nd.ten, nd.username, nd.email, " +
            "nd.gioiTinh, nd.trangThai, dc.dienThoai) " +
            "FROM NguoiDung nd " +
            "LEFT JOIN DiaChi dc ON nd.id = dc.nguoiDung.id " +
            "WHERE nd.vaiTro.id = 3")
    List<NguoiDungDTO> findAllKhachHangWithPhone();
}
