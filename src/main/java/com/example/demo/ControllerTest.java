package com.example.demo;

import com.example.demo.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {
    @Autowired
    private MauSacRepository msr;
    @GetMapping("/test")
    public String test(){
        return "Duy";
    }
    @GetMapping("/test2")
    public ResponseEntity<?> test2(){
        return ResponseEntity.ok(msr.findAll());
    }
}
