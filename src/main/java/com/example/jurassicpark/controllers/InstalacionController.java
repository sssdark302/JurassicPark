package com.example.jurassicpark.controllers;

import com.example.jurassicpark.exceptiones.InstalacionNotFoundException;
import com.example.jurassicpark.models.InstalacionDataStore;
import com.example.jurassicpark.models.InstalacionFactory;
import com.example.jurassicpark.repository.InstalacionRepository;
import com.example.jurassicpark.models.Instalacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instalaciones")
public class InstalacionController {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @GetMapping("/todasinstalaciones")
    public String getAllInstalaciones() {
        InstalacionDataStore dataStore = InstalacionDataStore.getInstance();
        return dataStore.getAllInstalacionesAsJSON();
    }

    @GetMapping("/{nombre}")
    public Instalacion getInstalacionByNombre(@PathVariable String nombre) {
        Instalacion instalacion = instalacionRepository.findInstalacionByNombre(nombre);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con nombre " + nombre + " no encontrada");
        }
    }

    @GetMapping("/tipo/{tipo}")
    public Instalacion getInstalacionByTipo(@PathVariable String tipo) {
        Instalacion instalacion = instalacionRepository.findInstalacionByTipo(tipo);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con tipo " + tipo + " no encontrada");
        }
    }

    @GetMapping("/id/{id}")
    public Instalacion getInstalacionById(@PathVariable int id) {
        Instalacion instalacion = instalacionRepository.findInstalacionById(id);
        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con id " + id + " no encontrada");
        }
    }
}
