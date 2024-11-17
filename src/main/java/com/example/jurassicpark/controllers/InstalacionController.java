package com.example.jurassicpark.controllers;

import com.example.jurassicpark.models.Instalacion;
import com.example.jurassicpark.models.InstalacionRequest;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosauriosPlantasFactory;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionService instalacionService;

    @Autowired
    @Lazy
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;



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

    @PostMapping("/{nombre}")
    public ResponseEntity<String> crearInstalacion(@PathVariable String nombre) {
        String mensaje;
        try {
            String tipoDeInstalacion = determinarTipoEspecificoDeInstalacion(nombre);
            dinosauriosPlantasFactory.crearInstalacionDinosauriosPlantas(
                    nombre, // nombre de la instalación
                    500, // capacidad predeterminada
                    500.0, // terreno predeterminado
                    "Alta", // nivel de seguridad predeterminado
                    "Descripción de instalación", // descripción predeterminada
                    10, // personal predeterminado
                    "08:00-20:00", // horario predeterminado
                    tipoDeInstalacion // tipo específico determinado por el nombre
            );
            mensaje = "Instalación creada con éxito: " + nombre;
            System.out.println(mensaje);
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            mensaje = "Error al crear instalación: " + e.getMessage();
            System.out.println(mensaje);
            return ResponseEntity.badRequest().body(mensaje);
        }
    }

    private String determinarTipoEspecificoDeInstalacion(String nombre) {
        if (nombre.contains("Acuatica") && nombre.contains("Carnivoro")) {
            return "Jaula_Acuatica_Carnivoro";
        } else if (nombre.contains("Acuatica") && nombre.contains("Omnivoro")) {
            return "Jaula_Acuatica_Omnivoro";
        } else if (nombre.contains("Terrestre") && nombre.contains("Carnivoro")) {
            return "Jaula_Terrestre_Carnivoro";
        } else if (nombre.contains("Terrestre") && nombre.contains("Herbivoro")) {
            return "Jaula_Terrestre_Herbivoro";
        } else if (nombre.contains("Terrestre") && nombre.contains("Omnivoro")) {
            return "Jaula_Terrestre_Omnivoro";
        } else if (nombre.contains("Aerea") && nombre.contains("Carnivoro")) {
            return "Jaula_Aerea_Carnivoro";
        } else if (nombre.contains("Aerea") && nombre.contains("Omnivoro")) {
            return "Jaula_Aerea_Omnivoro";
        } else {
            throw new IllegalArgumentException("Tipo de instalación no reconocido para el nombre: " + nombre);
        }
    }
}
