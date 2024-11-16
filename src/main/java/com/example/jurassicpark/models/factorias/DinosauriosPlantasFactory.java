package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.subclases.*;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioInstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DinosauriosPlantasFactory {

    @Autowired
    @Lazy
    private DinosaurioInstalacionService dinosaurioInstalacionService;

    @Autowired
    @Lazy
    private DinosaurioRepository dinosaurioRepository;


    public InstalacionE crearInstalacionDinosauriosPlantas(String nombre, int capacidad, double terreno, String seguridad,
                                                           String descripcion, int personal, String horario, String tipo) {
        InstalacionE nuevaInstalacion;

        // Determinar el tipo de instalación basado en el nombre
        String tipoDeInstalacion = determinarTipoDeInstalacion(nombre);

        // Determinar la categoría de dinosaurios basados en el tipo proporcionado
        String categoriaDinosaurio = determinarCategoriaDeDinosauriosPorTipo(tipo);

        switch (tipoDeInstalacion) {
            case "Acuatica" -> nuevaInstalacion = crearInstalacionAcuatica(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, categoriaDinosaurio);
            case "Terrestre" -> nuevaInstalacion = crearInstalacionTerrestre(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, categoriaDinosaurio);
            case "Aerea" -> nuevaInstalacion = crearInstalacionAerea(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, categoriaDinosaurio);
            default -> throw new IllegalArgumentException("Tipo de instalación desconocido: " + tipoDeInstalacion);
        }

        // Inicializar dinosaurios compatibles con esta instalación
        dinosaurioInstalacionService.inicializarDinosauriosParaInstalacion(nuevaInstalacion);

        return nuevaInstalacion;
    }

    private String determinarTipoDeInstalacion(String nombre) {
        if (nombre.toLowerCase().contains("acuatico")) {
            return "Acuatica";
        } else if (nombre.toLowerCase().contains("terrestre")) {
            return "Terrestre";
        } else if (nombre.toLowerCase().contains("aereo")) {
            return "Aerea";
        } else {
            throw new IllegalArgumentException("No se puede determinar el tipo de instalación a partir del nombre: " + nombre);
        }
    }

    private InstalacionE crearInstalacionAcuatica(String nombre, int capacidad, double terreno, String seguridad,
                                                  String descripcion, int personal, String horario, String tipo) {
        String categoriaDinosaurio = determinarCategoriaDeDinosauriosPorTipo(tipo);

        return switch (categoriaDinosaurio) {
            case "Carnivoro" -> new Jaula_Acuatica_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro" -> new Jaula_Acuatica_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Categoría de dinosaurios no compatible con instalación Acuática");
        };
    }

    private InstalacionE crearInstalacionTerrestre(String nombre, int capacidad, double terreno, String seguridad,
                                                   String descripcion, int personal, String horario, String categoriaDinosaurio) {
        return switch (categoriaDinosaurio) {
            case "Carnivoro" -> new Jaula_Terrestre_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Herbivoro" -> new Jaula_Terrestre_Herbivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro" -> new Jaula_Terrestre_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Categoría de dinosaurios no compatible con instalación Terrestre");
        };
    }

    private InstalacionE crearInstalacionAerea(String nombre, int capacidad, double terreno, String seguridad,
                                               String descripcion, int personal, String horario, String tipo) {
        String categoriaDinosaurio = determinarCategoriaDeDinosauriosPorTipo(tipo);

        return switch (categoriaDinosaurio) {
            case "Carnivoro" -> new Jaula_Aerea_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro" -> new Jaula_Aerea_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Categoría de dinosaurios no compatible con instalación Aérea");
        };
    }

    private String determinarCategoriaDeDinosauriosPorTipo(String tipo) {
        List<Dinos> dinosaurios = dinosaurioRepository.findByTipo(tipo);

        if (dinosaurios.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron dinosaurios del tipo: " + tipo);
        }

        // Dado que el tipo es consistente, puedes retornar directamente el tipo del primer dinosaurio encontrado
        String categoria = dinosaurios.get(0).getTipo();

        if ("Carnivoro".equalsIgnoreCase(categoria)) {
            return "Carnivoro";
        } else if ("Herbivoro".equalsIgnoreCase(categoria)) {
            return "Herbivoro";
        } else if ("Omnivoro".equalsIgnoreCase(categoria)) {
            return "Omnivoro";
        } else {
            throw new IllegalArgumentException("Tipo de dinosaurio no válido: " + categoria);
        }
    }
}
