package com.example.jurassicpark.repository;

import com.example.jurassicpark.models.Instalacion;
import com.example.jurassicpark.models.InstalacionDataStore;

public class InstalacionRepository {
    private InstalacionDataStore dataStore;

    public InstalacionRepository() {
        dataStore = InstalacionDataStore.getInstance();
    }

    public static Instalacion getInstalacionByNombre(String nombre) {
        return InstalacionDataStore.getInstalacion(nombre);
    }
}
