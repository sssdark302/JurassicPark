package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.service.InstalacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstalacionFactory {

    @Autowired
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;


    public InstalacionE crearInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad,
                                         String descripcion, int personal, String horario, String habitat, String dieta) {

        InstalacionE nuevaInstalacion = new InstalacionE(
                nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta
        );
        if ("Dinosaurios_Plantas".equals(tipo)) {
            dinosauriosPlantasFactory.crearInstalacionDinosauriosPlantas(
                    nombre, capacidad, terreno, seguridad, descripcion, personal, horario, habitat, dieta
            );
        }
        return nuevaInstalacion;
    }
}

