package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.subclases.Instalacion_Islas;
import com.example.jurassicpark.models.subclases.Turismo;
import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.stereotype.Component;

@Component
public class InstalacionFactory {

    public static InstalacionE crearInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario) {
        switch (tipo) {
            case "Turismo":
                return new Turismo(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            case "Instalacion_Islas":
                return new Instalacion_Islas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            case "Dinosaurios_Plantas":
                return Dinosaurios_PlantasFactory.crearInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
            default:
                throw new IllegalArgumentException("Tipo de dinosaurio desconocido: " + tipo);
        }
    }
}
