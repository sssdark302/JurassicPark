package com.example.jurassicpark.service;

import com.example.jurassicpark.models.InstalacionFactory;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @Autowired
    private InstalacionFactory instalacionFactory;

    public InstalacionE agregarInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario){
        InstalacionE instalacion = instalacionFactory.crearInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
        System.out.println("Se ha creado la instalación: " + instalacion);
        return instalacionRepository.save(instalacion);
    }
}
