package com.example.jurassicpark.controllers;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.service.DinosaurioService;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jurassicpark.repository.DinosaurioRepository;

import java.util.List;

@RestController
@RequestMapping("/dinosaurios")
public class DinosaurioController {

    @Autowired
    @Lazy
    private DinosaurioService dinosaurioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Dinos>> listarDinosaurios() {
        return ResponseEntity.ok(dinosaurioService.listarDinosaurios());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDinosaurio(@PathVariable int id) {
        dinosaurioService.eliminarDinosaurioPorId(id);
        return ResponseEntity.ok("Dinosaurio eliminado exitosamente.");
    }
}
