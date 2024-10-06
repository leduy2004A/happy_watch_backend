package com.example.demo.repository;

import com.example.demo.model.LoaiKinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiKinhRepository extends JpaRepository<LoaiKinh, Long> {
}
