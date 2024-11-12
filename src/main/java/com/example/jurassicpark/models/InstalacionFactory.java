package com.example.jurassicpark.models;

import com.example.jurassicpark.exceptiones.InstalacionNotFoundException;

public class InstalacionFactory {

    // Método para obtener o crear una instalación específica
    public Instalacion obtenerInstalacion(String nombre) {
        InstalacionDataStore dataStore = InstalacionDataStore.getInstance();
        Instalacion instalacion = dataStore.getInstalacion(nombre);

        if (instalacion != null) {
            return instalacion;
        } else {
            throw new InstalacionNotFoundException("Instalación con nombre " + nombre + " no encontrada");
        }
    }
}
