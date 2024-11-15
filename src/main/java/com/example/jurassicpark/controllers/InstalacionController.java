package com.example.jurassicpark.controllers;

import com.example.jurassicpark.exceptiones.InstalacionNotFoundException;
import com.example.jurassicpark.models.datastores.InstalacionDataStore;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @Autowired
    private DinosaurioInstalacionRepository dinosaurioInstalacionRepository;

    @GetMapping("/todasinstalaciones")
    public String getAllInstalaciones() {
        InstalacionDataStore dataStore = InstalacionDataStore.getInstance();
        return dataStore.getAllInstalacionesAsJSON();
    }

    @GetMapping("/{nombre}")
    public InstalacionE getInstalacionByNombre(@PathVariable String nombre) {
        InstalacionE instalacion = instalacionRepository.findInstalacionByNombre(nombre);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con nombre " + nombre + " no encontrada");
        }
    }

    @GetMapping("/tipo/{tipo}")
    public InstalacionE getInstalacionByTipo(@PathVariable String tipo) {
        InstalacionE instalacion = instalacionRepository.findInstalacionByTipo(tipo);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con tipo " + tipo + " no encontrada");
        }
    }

    @GetMapping("/dinosaurios/{instalacion}")
    public List<DinosaurioInstalaciones> getDinosauriosByInstalacion(@PathVariable InstalacionE instalacion) {
        List<DinosaurioInstalaciones> dinosaurioInstalaciones = dinosaurioInstalacionRepository.findByInstalacion(instalacion);
        if (dinosaurioInstalaciones != null) {
            return dinosaurioInstalaciones;
        } else {
            throw new InstalacionNotFoundException("Dinosaurios en la instalación " + instalacion + " no encontrados");
        }
    }
}
