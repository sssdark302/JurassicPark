package com.example.jurassicpark.models;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DinosaurioDataStore {
    private static DinosaurioDataStore instance;
    private Map<String, Dinosaurio> dinosaurios;

    private DinosaurioDataStore() {
        dinosaurios = new HashMap<>();
        cargarDatosCSV("data/datos-dinos.csv");
    }

    public static synchronized DinosaurioDataStore getInstance() {
        if (instance == null) {
            instance = new DinosaurioDataStore();
        }
        return instance;
    }

    private void cargarDatosCSV(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine(); // Saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                String especie = campos[0];
                int edad = Integer.parseInt(campos[1]);
                double alturaMaxima = Double.parseDouble(campos[2]);
                int pesoMaximo = Integer.parseInt(campos[3]);
                char sexo = campos[4].charAt(0);
                double hpMaxima = Double.parseDouble(campos[5]);
                String tipo = campos[6];

                Dinosaurio dino;
                switch (tipo) {
                    case "Carnivoro":
                        dino = new Carnivoro(especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima);
                        break;
                    case "Herbivoro":
                        dino = new Herbivoro(especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima);
                        break;
                    case "Omnivoro":
                        dino = new Omnivoro(especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de dinosaurio desconocido: " + tipo);
                }

                dinosaurios.put(especie, dino);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dinosaurio getDinosaurio(String especie) {
        return dinosaurios.get(especie);
    }
}
