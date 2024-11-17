package com.example.jurassicpark.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @GetMapping("/data")
    public String getProtectedData() {
        return "Este es un dato protegido, solo accesible para usuarios autenticados.";
    }
}
