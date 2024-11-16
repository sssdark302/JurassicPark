package com.example.jurassicpark.models.datastores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.InstalacionRepository;
import com.example.jurassicpark.service.InstalacionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.stream;

@Service
public class InstalacionDataStore {

    private static InstalacionDataStore instance;

    @Autowired
    private static InstalacionRepository instalacionRepository;

    @Autowired
    private InstalacionService instalacionService;

      private InstalacionDataStore() {
        cargarDatosCSV("data/datos-instalaciones.csv");
    }

    public static synchronized InstalacionDataStore getInstance() {
        if (instance == null) {
            instance = new InstalacionDataStore();
        }
        return instance;
    }

    public void cargarDatosCSV(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
               instalacionService.crearInstalacion(campos[0], Integer.parseInt(campos[1]), campos[2], Double.parseDouble(campos[3]), campos[4],
                        campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
