package com.example.jurassicpark.controllers;

import com.example.jurassicpark.exceptiones.InstalacionNotFoundException;
import com.example.jurassicpark.models.InstalacionDataStore;
import com.example.jurassicpark.models.InstalacionFactory;
import com.example.jurassicpark.repository.InstalacionRepository;
import com.example.jurassicpark.models.Instalacion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instalaciones")
public class InstalacionController {
    private InstalacionFactory factory;
    private InstalacionRepository view;

    public InstalacionController() {
        factory = new InstalacionFactory();
        view = new InstalacionRepository();
    }

    public void mostrarInstalacion(String nombre) {
        Instalacion instalacion = factory.obtenerInstalacion(nombre);
        if (instalacion != null) {
            view.mostrarInstalacion(instalacion);
        } else {
            System.out.println("Instalación no encontrada.");
        }
    }

    @GetMapping
    public String getAllInstalaciones() {
        InstalacionDataStore dataStore = InstalacionDataStore.getInstance();
        return dataStore.getAllInstalacionesAsJSON();
    }

    @GetMapping("/{nombre}")
    public Instalacion getInstalacionByNombre(@PathVariable String nombre) {
        Instalacion instalacion = InstalacionRepository.getInstalacionByNombre(nombre);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con nombre " + nombre + " no encontrada");
        }
    }
}
