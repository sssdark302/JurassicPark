package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class InstalacionFactory {

    @Autowired
    @Lazy
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;

    public InstalacionE crearInstalacion(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String tipo) {

        InstalacionE nuevaInstalacion;

        if ("Dinosaurios_Plantas".equals(tipo)) {
            nuevaInstalacion = dinosauriosPlantasFactory.crearInstalacionDinosauriosPlantas(
                    nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
        } else {
            nuevaInstalacion = new InstalacionE(
                    nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo
            );
        }

        return nuevaInstalacion;
    }
}
