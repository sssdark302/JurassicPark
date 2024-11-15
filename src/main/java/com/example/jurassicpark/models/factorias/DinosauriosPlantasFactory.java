package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.subclases.*;
import org.springframework.stereotype.Component;

@Component
public class DinosauriosPlantasFactory {
    public static InstalacionE crearInstalacionDinosauriosPlantas(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        switch (habitat) {
            case "Acuatico":
                return crearInstalacionAcuatica(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, dieta);
            case "Terrestre":
                return crearInstalacionTerrestre(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, dieta);
            case "Aereo":
                return crearInstalacionAerea(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, dieta);
            default:
                throw new IllegalArgumentException("Hábitat desconocido: " + habitat);
        }
    }

    private static InstalacionE crearInstalacionAcuatica(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String dieta) {
        switch (dieta) {
            case "Carnivoro":
                return new Jaula_Acuatica_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro":
                return new Jaula_Acuatica_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default:
                throw new IllegalArgumentException("Dieta " + dieta + " no compatible con hábitat acuático");
        }
    }

    private static InstalacionE crearInstalacionTerrestre(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String dieta) {
        switch (dieta) {
            case "Carnivoro":
                return new Jaula_Terrestre_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Herbivoro":
                return new Jaula_Terrestre_Herbivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro":
                return new Jaula_Terrestre_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default:
                throw new IllegalArgumentException("Dieta " + dieta + " no compatible con hábitat terrestre");
        }
    }

    private static InstalacionE crearInstalacionAerea(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String dieta) {
        switch (dieta) {
            case "Carnivoro":
                return new Jaula_Aerea_Carnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            case "Omnivoro":
                return new Jaula_Aerea_Omnivoro(nombre, capacidad, "Dinosaurios_Plantas", terreno, seguridad, descripcion, personal, horario);
            default:
                throw new IllegalArgumentException("Dieta " + dieta + " no compatible con hábitat aéreo");
        }
    }
}
