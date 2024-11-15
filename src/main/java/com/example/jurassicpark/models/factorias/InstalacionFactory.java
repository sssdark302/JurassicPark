package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.subclases.Instalacion_Islas;
import com.example.jurassicpark.models.subclases.Turismo;
import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstalacionFactory {

    @Autowired
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;

    public InstalacionE crearInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        switch (tipo) {
            case "Turismo":
                return new Turismo(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
            case "Instalacion_Islas":
                return new Instalacion_Islas(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
            case "Dinosaurios_Plantas":
                return dinosauriosPlantasFactory.crearInstalacionDinosauriosPlantas(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
            default:
                throw new IllegalArgumentException("Tipo de instalación desconocido: " + tipo);
        }
    }
}
