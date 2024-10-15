package com.example.demo.controller;

import com.example.demo.appException.AppException;
import com.example.demo.dto.MessageDTO;
import com.example.demo.dto.tokenDTO;
import com.example.demo.service.UserService;
import com.example.demo.ultil.NguoiDungUltil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService us;

    @PostMapping("/register")
    public ResponseEntity<?> nguoiDungRegisterController(@RequestBody Map<String,Object> dataUser)
    {
        try{
           tokenDTO token = us.registerUser(dataUser);
           return ResponseEntity.ok(token);
        }
        catch(AppException e)
        {
            return ResponseEntity.status(e.getCode()).body(new MessageDTO(e.getMessage()));
        }
    }
}
