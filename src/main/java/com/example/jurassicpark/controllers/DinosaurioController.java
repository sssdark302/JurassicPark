package com.example.jurassicpark.controllers;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioInstalacionService;
import com.example.jurassicpark.service.DinosaurioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/dinosaurios")
public class DinosaurioController {

    @Autowired
    @Lazy
    private DinosaurioService dinosaurioService;

    @Autowired
    @Lazy
    private DinosaurioFactory dinosaurioFactory;

    @Autowired
    @Lazy
    private DinosaurioRepository dinosaurioRepository;

    // Obtener todos los dinosaurios
    @GetMapping
    public ResponseEntity<List<Dinos>> obtenerDinosaurios() {
        List<Dinos> dinosaurios = dinosaurioService.obtenerTodosLosDinosaurios();
        return ResponseEntity.ok(dinosaurios);
    }
    @PostMapping
    public ResponseEntity<Dinos> crearEspecifico(@RequestBody String dinos) {
        Dinos nuevoDinosaurio = new Dinos();

        switch (dinos.toLowerCase()) {
            case "t-rex" -> {
                nuevoDinosaurio.setEspecie("T-Rex");
                nuevoDinosaurio.setTipo("Carnívoro");
            }
            case "velociraptor" -> {
                nuevoDinosaurio.setEspecie("Velociraptor");
                nuevoDinosaurio.setTipo("Carnívoro");
            }
            case "mosasaurus" -> {
                nuevoDinosaurio.setEspecie("Mosasaurus");
                nuevoDinosaurio.setTipo("Carnívoro");
            }
            case "triceratops" -> {
                nuevoDinosaurio.setEspecie("Triceratops");
                nuevoDinosaurio.setTipo("Herbívoro");
            }
            case "stegosaurus" -> {
                nuevoDinosaurio.setEspecie("Stegosaurus");
                nuevoDinosaurio.setTipo("Herbívoro");
            }
            case "pteranodon" -> {
                nuevoDinosaurio.setEspecie("Pteranodon");
                nuevoDinosaurio.setTipo("Carnívoro");
            }
            case "quetzalcoatlus" -> {
                nuevoDinosaurio.setEspecie("Quetzalcoatlus");
                nuevoDinosaurio.setTipo("Carnívoro");
            }
            default -> throw new IllegalArgumentException("Dinosaurio no válido: " + dinos);
        }

        dinosaurioRepository.save(nuevoDinosaurio);

        return ResponseEntity.ok(nuevoDinosaurio);
    }
}
