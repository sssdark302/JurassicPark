package com.example.jurassicpark.models;

import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.entidades.DinosaurioInstalacion;
import org.springframework.stereotype.Component;

@Component
public class DinosauriosPlantasFactory {

    public static InstalacionE crearInstalacionJaula(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        switch (nombre) {
            case "Jaula_Carnivoro":
                return new Dinosaurios_Plantas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            case "Jaula_Herbivoro":
                return new Dinosaurios_Plantas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            case "Jaula_Omnivoro":
                return new Dinosaurios_Plantas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            default:
                throw new IllegalArgumentException("Nombre de jaula desconocido: " + nombre);
        }
    }
}
