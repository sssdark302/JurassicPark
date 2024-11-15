package com.example.jurassicpark.models.datastores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.example.jurassicpark.repository.InstalacionRepository;
import com.example.jurassicpark.service.InstalacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstalacionDataStore {

    private static InstalacionDataStore instance;

    @Autowired
    private InstalacionService instalacionService;

    @Autowired
    private static InstalacionRepository instalacionRepository;

    private InstalacionDataStore() {
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
                double terreno = Double.parseDouble(campos[3]);
                String seguridad = campos[4];
                String descripcion = campos[5];
                int personal = Integer.parseInt(campos[6]);
                String horario = campos[7];
                String habitat = campos[8];
                String dieta = campos[9];

                generarInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generarInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        instalacionService.crearYAlmacenarInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
    }

    public String getAllInstalacionesAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(instalacionRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
