package com.example.jurassicpark.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InstalacionDataStore {

    private static InstalacionDataStore instance;
    private static Map<String, Instalacion> instalaciones = new HashMap<>();
    private InstalacionFactory instalacionFactory;

    private InstalacionDataStore() {
        instalacionFactory = new InstalacionFactory();
        cargarDatosCSV("data/datos-instalaciones.csv");
    }

    public static synchronized InstalacionDataStore getInstance() {
        if (instance == null) {
            instance = new InstalacionDataStore();
        }
        return instance;
    }

    private void cargarDatosCSV(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                String nombre = campos[0];
                int capacidad = Integer.parseInt(campos[1]);
                String tipo = campos[2];
                int terreno = Integer.parseInt(campos[3]);
                String seguridad = campos[4];
                String descripcion = campos[5];
                int personal = Integer.parseInt(campos[6]);
                String horario = campos[7];
                Instalacion instalacion = instalacionFactory.crearInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario);
                instalaciones.put(instalacion.getNombre(), instalacion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Instalacion getInstalacion(String nombre) {
        return instalaciones.get(nombre);
    }

    public Collection<Instalacion> getAllInstalaciones() {
        return instalaciones.values();
    }

    public String getAllInstalacionesAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(instalaciones);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
