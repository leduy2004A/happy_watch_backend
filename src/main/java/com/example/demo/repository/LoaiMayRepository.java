package com.example.demo.repository;

import com.example.demo.model.LoaiMay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiMayRepository extends JpaRepository<LoaiMay, Long> {
}
