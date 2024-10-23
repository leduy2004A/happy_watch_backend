package com.example.demo.appException;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppException extends RuntimeException{
    private int code;
    private String message;
}
