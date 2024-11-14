package com.example.jurassicpark.models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DinosaurioDataStore {

    private static DinosaurioDataStore instance;

    @Autowired
    private DinosaurioService dinosaurioService;

    @Autowired
    private DinosaurioFactory dinosaurioFactory;

    @Autowired
    private static DinosaurioRepository dinosaurioRepository;

    private DinosaurioDataStore() {
        cargarDatosCSV("data/datos-dinos.csv");
    }

    public static synchronized DinosaurioDataStore getInstance() {
        if (instance == null) {
            instance = new DinosaurioDataStore();
        }
        return instance;
    }

    private synchronized void cargarDatosCSV(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine(); // Saltar el encabezado del CSV
            while ((linea = br.readLine()) != null) { // Leer las líneas mientras no sean nulas
                String[] campos = linea.split(",");
                String especie = campos[0];
                int edad = Integer.parseInt(campos[1]);
                double alturaMaxima = Double.parseDouble(campos[2]);
                int pesoMaximo = Integer.parseInt(campos[3]);
                Sexo sexo = randomSexo();
                double hpMaxima = Double.parseDouble(campos[4]);
                String tipo = campos[5];
                boolean tuvoHijos = Boolean.parseBoolean(campos[6]);
                FaseCicloDeVida faseCicloDeVida = FaseCicloDeVida.HUEVO;
                generarDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos, faseCicloDeVida);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Sexo randomSexo() {
        return new Random().nextBoolean() ? Sexo.MACHO : Sexo.HEMBRA;
    }

    private void generarDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo, Sexo sexo, double hpMaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida) {
        //creo nuevo dinosaurio con los datos del CSV
        //si en el mapa hay mas de 2 dinosaurios de la misma especie, no se agrega

        dinosaurioService.agregarDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos, faseCicloDeVida);
    }
    public String getAllDinosauriosAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //  map completo de dinosaurios a JSON
            return mapper.writeValueAsString(dinosaurioFactory);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
