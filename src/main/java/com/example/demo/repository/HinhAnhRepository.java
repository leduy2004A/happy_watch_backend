package com.example.demo.repository;

import com.example.demo.model.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, Long> {
    @Query("""
    select ha.urlImage from HinhAnh ha join SanPham sp on ha.sanPham.id = sp.id
    where sp.id = :id
""")
    List<String> getHinhAnhsByIdSanPham (@Param("id")Long id);
}
