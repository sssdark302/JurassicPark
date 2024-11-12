package com.example.jurassicpark.models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DinosaurioDataStore {
    private static DinosaurioDataStore instance;
    private static Map<String, Dinosaurio> dinosaurios = new ConcurrentHashMap<>();
    private DinosaurioFactory dinosaurioFactory;

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

                Dinosaurio dino = dinosaurioFactory.crearDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos);
                dinosaurios.put(especie, dino);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Dinosaurio getDinosaurio(String especie) {
        return dinosaurios.get(especie);
    }

    public Collection<Dinosaurio> getAllDinosaurios() {
        return dinosaurios.values();
    }

    private Sexo randomSexo() {
        return new Random().nextBoolean() ? Sexo.MACHO : Sexo.HEMBRA;
    }

    public String getAllDinosauriosAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //  map completo de dinosaurios a JSON
            return mapper.writeValueAsString(dinosaurios);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }
}
