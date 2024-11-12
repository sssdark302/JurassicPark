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
    private static Map<String, Instalacion> instalaciones;

    private InstalacionDataStore() {
        instalaciones = new HashMap<>();
        cargarDatosCSV("data/datos-instalaciones.csv"); // Cambia la ruta del archivo CSV según tu proyecto
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
            br.readLine(); // Saltar la cabecera del CSV

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");

                // Crear la instalación usando el InstalacionBuilder
                Instalacion instalacion = new InstalacionBuilder()
                        .setNombre(campos[0].trim())
                        .setCapacidad(Integer.parseInt(campos[1].trim()))
                        .setTipo(campos[2].trim())
                        .setTerreno(Double.parseDouble(campos[3].trim()))
                        .setSeguridad(campos[4].trim())
                        .setDescripcion(campos[5].trim())
                        .setPersonal(Integer.parseInt(campos[6].trim()))
                        .setHorario(campos[7].trim())
                        .build();

                instalaciones.put(instalacion.getNombre(), instalacion); // Usar el nombre como clave
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Instalacion getInstalacion(String nombre) {
        return instalaciones.get(nombre);
    }

    public Collection<Instalacion> getAllInstalaciones() {
        return instalaciones.values();
    }

    // Convertir todas las instalaciones a JSON
    public String getAllInstalacionesAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convierte el map completo de instalaciones a JSON
            return mapper.writeValueAsString(instalaciones);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
