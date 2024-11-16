package com.example.jurassicpark.controllers;

import com.example.jurassicpark.exceptiones.InstalacionNotFoundException;
import com.example.jurassicpark.models.Instalacion;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @Autowired
    private DinosaurioInstalacionRepository dinosaurioInstalacionRepository;

    @Autowired
    private InstalacionService instalacionService;

    @GetMapping("/listar")
    public ResponseEntity<List<InstalacionE>> listarInstalaciones() {
        return ResponseEntity.ok(instalacionService.listarInstalaciones());
    }

    @GetMapping("/{nombre}")
    public InstalacionE getInstalacionByNombre(@PathVariable String nombre) {
        InstalacionE instalacion = instalacionRepository.findInstalacionByNombre(nombre);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con nombre " + nombre + " no encontrada");
        }
    }

    @GetMapping("/tipo/{tipo}")
    public List<InstalacionE> getInstalacionByTipo(@PathVariable String tipo) {
        List<InstalacionE> instalacion = instalacionRepository.findByTipo(tipo);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con tipo " + tipo + " no encontrada");
        }
    }

    @GetMapping("/dinosaurios/{instalacion}")
    public List<DinosaurioInstalaciones> getDinosauriosByInstalacion(@PathVariable InstalacionE instalacion) {
        List<DinosaurioInstalaciones> dinosaurioInstalaciones = dinosaurioInstalacionRepository.findByInstalacion(instalacion);
        if (dinosaurioInstalaciones != null) {
            return dinosaurioInstalaciones;
        } else {
            throw new InstalacionNotFoundException("Dinosaurios en la instalación " + instalacion + " no encontrados");
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarInstalacion(@RequestParam String tipo) {
        try {
            InstalacionE nuevaInstalacion = instalacionService.crearInstalacionPorTipo(tipo);
            return ResponseEntity.ok("Instalación creada exitosamente: " + nuevaInstalacion.getNombre());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarInstalacion(@PathVariable int id) {
        try {
            instalacionService.eliminarInstalacionPorId(id);
            return ResponseEntity.ok("Instalación eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
