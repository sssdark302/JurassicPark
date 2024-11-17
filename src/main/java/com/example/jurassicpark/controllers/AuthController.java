package com.example.jurassicpark.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        // Spring Security maneja el login automáticamente
        return ResponseEntity.ok("Inicio de sesión exitoso");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Spring Security maneja el logout automáticamente
        return ResponseEntity.ok("Sesión cerrada con éxito");
    }

    @GetMapping("/authenticated")
    public ResponseEntity<?> checkAuthentication(HttpServletRequest request) {
        boolean authenticated = request.getUserPrincipal() != null;
        return ResponseEntity.ok().body("{\"authenticated\": " + authenticated + "}");
    }
}
