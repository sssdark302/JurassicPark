package com.example.jurassicpark.controllers;

import com.example.jurassicpark.models.InstalacionRequest;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionService instalacionService;

    // Obtener todas las instalaciones
    @GetMapping
    public ResponseEntity<List<InstalacionE>> obtenerInstalaciones() {
        List<InstalacionE> instalaciones = instalacionService.obtenerTodasLasInstalaciones();
        return ResponseEntity.ok(instalaciones);
    }

    // Crear una nueva instalación
    @PostMapping
    public ResponseEntity<InstalacionE> crearInstalacion(@RequestBody InstalacionRequest request) {
        InstalacionE nuevaInstalacion = instalacionService.crearInstalacion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInstalacion);
    }

}
