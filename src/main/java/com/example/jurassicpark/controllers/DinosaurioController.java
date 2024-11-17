package com.example.jurassicpark.controllers;
import com.example.jurassicpark.ciclodevida.GestorCV;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.service.DinosaurioInstalacionService;
import com.example.jurassicpark.service.DinosaurioService;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jurassicpark.repository.DinosaurioRepository;

import java.util.List;

@RestController
@RequestMapping("/api/dinosaurios")
public class DinosaurioController {

    @Autowired
    @Lazy
    private DinosaurioService dinosaurioService;

    @Autowired
    @Lazy
    private DinosaurioInstalacionService dinosaurioInstalacionService;

    // Obtener todos los dinosaurios
    @GetMapping
    public ResponseEntity<List<Dinos>> obtenerDinosaurios() {
        List<Dinos> dinosaurios = dinosaurioService.obtenerTodosLosDinosaurios();
        return ResponseEntity.ok(dinosaurios);
    }
/*
    // Filtrar dinosaurios por instalación
    @GetMapping("/instalacion/{id}")
    public ResponseEntity<List<Dinos>> obtenerDinosauriosPorInstalacion(@PathVariable int id) {
        List<Dinos> dinosaurios = dinosaurioInstalacionService.obtenerDinosauriosPorInstalacion(id);
        return ResponseEntity.ok(dinosaurios);
    }

 */
}
