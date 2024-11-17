package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.subclases.*;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioInstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InstalacionE crearInstalacionDinosauriosPlantas(String nombre, int capacidad, double terreno, String seguridad,
                                                           String descripcion, int personal, String horario, String tipo) {
        InstalacionE nuevaInstalacion;

        if (!"Dinosaurios_Plantas".equals(tipo)) {
            throw new IllegalArgumentException("Este método solo maneja instalaciones de tipo Dinosaurios_Plantas");
        }

        String tipoDeInstalacion = determinarTipoEspecificoDeInstalacion(nombre);

        // Crear instalación específica
        switch (tipoDeInstalacion) {
            case "Jaula_Acuatica_Carnivoro" ->
                    nuevaInstalacion = new Jaula_Acuatica_Carnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            case "Jaula_Acuatica_Omnivoro" ->
                    nuevaInstalacion = new Jaula_Acuatica_Omnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            case "Jaula_Terrestre_Carnivoro" ->
                    nuevaInstalacion = new Jaula_Terrestre_Carnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            case "Jaula_Terrestre_Herbivoro" ->
                    nuevaInstalacion = new Jaula_Terrestre_Herbivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            case "Jaula_Terrestre_Omnivoro" ->
                    nuevaInstalacion = new Jaula_Terrestre_Omnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            case "Jaula_Aerea_Carnivoro" ->
                    nuevaInstalacion = new Jaula_Aerea_Carnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            case "Jaula_Aerea_Omnivoro" ->
                    nuevaInstalacion = new Jaula_Aerea_Omnivoro(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipoDeInstalacion);
            default ->
                    throw new IllegalArgumentException("Tipo de instalación desconocido: " + tipoDeInstalacion);
        }

        // Guardar en tabla temporal
        guardarEnTablaTemporal(nuevaInstalacion);

        // Asignar dinosaurios compatibles
        List<Dinos> dinosauriosCompatibles = obtenerDinosauriosCompatiblesPorReglas(nuevaInstalacion);
        dinosaurioInstalacionService.asignarDinosauriosAInstalacion(dinosauriosCompatibles, nuevaInstalacion);

        return nuevaInstalacion;
    }

    private void guardarEnTablaTemporal(InstalacionE instalacion) {
        jdbcTemplate.update("""
            INSERT INTO temp_instalaciones (nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """, instalacion.getNombre(), instalacion.getCapacidad(), instalacion.getTerreno(),
                instalacion.getSeguridad(), instalacion.getDescripcion(), instalacion.getPersonal(),
                instalacion.getHorario(), instalacion.getTipo());
    }

    public List<Dinos> obtenerDinosauriosCompatiblesPorReglas(InstalacionE instalacion) {
        String tipoDeInstalacion = instalacion.getTipo();

        // Filtrar dinosaurios desde la base de datos
        return dinosaurioRepository.findAll().stream()
                .filter(dino -> esDinosaurioCompatibleConInstalacion(dino, tipoDeInstalacion))
                .collect(Collectors.toList());
    }

    public boolean esDinosaurioCompatibleConInstalacion(Dinos dinosaurio, String tipoDeInstalacion) {
        return switch (tipoDeInstalacion) {
            case "Acuatica" -> List.of("Mosasaurus", "Plesiosaurus", "Ichthyosaurus", "Liopleurodon").contains(dinosaurio.getEspecie());
            case "Terrestre" -> List.of("T-Rex", "Triceratops", "Velociraptor", "Stegosaurus").contains(dinosaurio.getEspecie());
            case "Aerea" -> List.of("Pteranodon", "Quetzalcoatlus", "Dsungaripterus", "Rhamphorhynchus").contains(dinosaurio.getEspecie());
            default -> false;
        };
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
            throw new IllegalArgumentException("No se puede determinar el tipo específico de instalación para el nombre: " + nombre);
        }
    }

    public boolean esDinosaurioAcuaticoCarnivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Acuatica_Carnivoro
        return List.of("Mosasaurus", "Plesiosaurus", "Ichthyosaurus", "Liopleurodon")
                .contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioAcuaticoOmnivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Acuatica_Omnivoro (si no hay ninguno, retorna falso)
        return false; // No hay especies registradas como omnívoras acuáticas
    }

    public boolean esDinosaurioTerrestreCarnivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Terrestre_Carnivoro
        return List.of("T-Rex", "Velociraptor").contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioTerrestreHerbivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Terrestre_Herbivoro
        return List.of("Triceratops", "Stegosaurus").contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioTerrestreOmnivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Terrestre_Omnivoro
        return List.of("Gallimimus", "Therizinosaurus").contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioAereoCarnivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Aerea_Carnivoro
        return List.of("Pteranodon", "Quetzalcoatlus", "Dsungaripterus", "Rhamphorhynchus")
                .contains(dinosaurio.getEspecie());
    }

    public boolean esDinosaurioAereoOmnivoro(Dinos dinosaurio) {
        // Reglas específicas para Jaula_Aerea_Omnivoro
        return List.of("Microraptor", "Archaeopteryx").contains(dinosaurio.getEspecie());
    }

}
