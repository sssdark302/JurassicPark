package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.subclases.*;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
public class DinosauriosPlantasFactory {

    @Autowired
    @Lazy
    private InstalacionService instalacionService;

    public InstalacionE crearInstalacionDinosauriosPlantas(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        InstalacionE nuevaInstalacion;

        switch (habitat) {
            case "Acuatico" -> nuevaInstalacion = crearInstalacionAcuatica(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, dieta);
            case "Terrestre" -> nuevaInstalacion = crearInstalacionTerrestre(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, dieta);
            case "Aereo" -> nuevaInstalacion = crearInstalacionAerea(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, dieta);
            default -> throw new IllegalArgumentException("Hábitat desconocido: " + habitat);
        }
        instalacionService.inicializarDinosauriosParaInstalacion(nuevaInstalacion, habitat, dieta);
        return nuevaInstalacion;
    }

    private InstalacionE crearInstalacionAcuatica(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String dieta) {
        return switch (dieta) {
            case "Carnivoro" -> new Jaula_Acuatica_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro" -> new Jaula_Acuatica_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Dieta " + dieta + " no compatible con hábitat acuático");
        };
    }

    private InstalacionE crearInstalacionTerrestre(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String dieta) {
        return switch (dieta) {
            case "Carnivoro" -> new Jaula_Terrestre_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Herbivoro" -> new Jaula_Terrestre_Herbivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro" -> new Jaula_Terrestre_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Dieta " + dieta + " no compatible con hábitat terrestre");
        };
    }

    private InstalacionE crearInstalacionAerea(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String dieta) {
        return switch (dieta) {
            case "Carnivoro" -> new Jaula_Aerea_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro" -> new Jaula_Aerea_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default -> throw new IllegalArgumentException("Dieta " + dieta + " no compatible con hábitat aéreo");
        };
    }
}
