package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.subclases.Jaula_Acuatica_Omnivoro;
import com.example.jurassicpark.models.subclases.Jaula_Aerea_Carnivoro;
import com.example.jurassicpark.models.subclases.Jaula_Terrestre_Herbivoro;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioInstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;

import java.util.stream.Collectors;



@Component
public class DinosauriosPlantasFactory {

    @Autowired
    @Lazy
    private DinosaurioInstalacionService dinosaurioInstalacionService;

    @Autowired
    @Lazy
    private DinosaurioRepository dinosaurioRepository;

    public InstalacionE crearInstalacionDinosauriosPlantas(String nombre, int capacidad, double terreno, String seguridad,
                                                           String descripcion, int personal, String horario, String tipoInstalacion) {
        InstalacionE nuevaInstalacion;

        // Determinar el tipo de instalación basado en el nombre
        String tipoDeInstalacion = determinarTipoDeInstalacion(nombre);

        // Crear la instalación según su tipo
        switch (tipoDeInstalacion) {
            case "Acuatica" -> nuevaInstalacion = crearInstalacionAcuatica(nombre, capacidad, terreno, seguridad, descripcion, personal, horario);
            case "Terrestre" -> nuevaInstalacion = crearInstalacionTerrestre(nombre, capacidad, terreno, seguridad, descripcion, personal, horario);
            case "Aerea" -> nuevaInstalacion = crearInstalacionAerea(nombre, capacidad, terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Tipo de instalación desconocido: " + tipoDeInstalacion);
        }

        // Asignar dinosaurios compatibles a la instalación
        dinosaurioInstalacionService.inicializarDinosauriosParaInstalacion(nuevaInstalacion);

        return nuevaInstalacion;
    }

    private InstalacionE crearInstalacionAcuatica(String nombre, int capacidad, double terreno, String seguridad,
                                                  String descripcion, int personal, String horario) {
        return new Jaula_Acuatica_Omnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, "Acuatica");
    }

    private InstalacionE crearInstalacionTerrestre(String nombre, int capacidad, double terreno, String seguridad,
                                                   String descripcion, int personal, String horario) {
        return new Jaula_Terrestre_Herbivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, "Terrestre");
    }

    private InstalacionE crearInstalacionAerea(String nombre, int capacidad, double terreno, String seguridad,
                                               String descripcion, int personal, String horario) {
        return new Jaula_Aerea_Carnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, "Aerea");
    }

    private String determinarTipoDeInstalacion(String nombre) {
        if (nombre.contains("Acuatica")) {
            return "Acuatica";
        } else if (nombre.contains("Terrestre")) {
            return "Terrestre";
        } else if (nombre.contains("Aerea")) {
            return "Aerea";
        } else {
            throw new IllegalArgumentException("No se puede determinar el tipo de instalación para el nombre: " + nombre);
        }
    }

    public List<Dinos> obtenerDinosauriosCompatiblesPorReglas(InstalacionE instalacion) {
        String tipoDeInstalacion = instalacion.getTipo(); // Tipo: Acuatica, Terrestre, Aerea

        // Obtener todos los dinosaurios de la base de datos
        List<Dinos> dinosaurios = dinosaurioRepository.findAll();

        // Filtrar según las reglas de compatibilidad
        return dinosaurios.stream()
                .filter(dino -> esDinosaurioCompatibleConInstalacion(dino, tipoDeInstalacion))
                .collect(Collectors.toList());
    }

    private boolean esDinosaurioCompatibleConInstalacion(Dinos dinosaurio, String tipoDeInstalacion) {
        // Reglas de compatibilidad
        return switch (tipoDeInstalacion) {
            case "Acuatica" -> esDinosaurioAcuatico(dinosaurio);
            case "Terrestre" -> esDinosaurioTerrestre(dinosaurio);
            case "Aerea" -> esDinosaurioAereo(dinosaurio);
            default -> false;
        };
    }

    public boolean esDinosaurioAcuatico(Dinos dinosaurio) {
        return List.of("Mosasaurus", "Plesiosaurus", "Ichthyosaurus", "Liopleurodon").contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioTerrestre(Dinos dinosaurio) {
        return List.of("Tyrannosaurus", "Triceratops", "Stegosaurus", "Velociraptor").contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioAereo(Dinos dinosaurio) {
        return List.of("Pterodactyl", "Quetzalcoatlus", "Dimorphodon", "Tapejara").contains(dinosaurio.getEspecie());
    }

}
