package com.example.demo.repository;

import com.example.demo.dto.NguoiDungDTO;
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
    @Query("SELECT new com.example.demo.dto.NguoiDungDTO(nd.id, nd.ma, nd.avatar, nd.ten, nd.username, nd.email, " +
            "nd.gioiTinh, nd.trangThai, nd.dienThoai) " +
            "FROM NguoiDung nd " +
            "WHERE nd.id <> 1")
    List<NguoiDungDTO> findAllKhachHangWithPhone();
}
