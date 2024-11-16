package com.example.jurassicpark.models.datastores;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DinosaurioDataStore {

    private static DinosaurioDataStore instance;


    private static DinosaurioService dinosaurioService;

    @Autowired
    @Lazy
    private DinosaurioFactory dinosaurioFactory;

    @Autowired
    @Lazy
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

    public void cargarDatosCSV(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            String encabezado = br.readLine(); // Leer encabezado y descartarlo
            if (encabezado == null || encabezado.isEmpty()) {
                throw new IOException("El archivo CSV está vacío o no tiene encabezado.");
            }

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length < 7) { // Verificar que haya al menos 7 columnas
                    System.err.println("Línea inválida en CSV (menos de 7 campos): " + linea);
                    continue; // Saltar líneas inválidas
                }

                try {
                    String especie = campos[0].trim();
                    int edad = Integer.parseInt(campos[1].trim());
                    double alturaMaxima = Double.parseDouble(campos[2].trim());
                    int pesoMaximo = Integer.parseInt(campos[3].trim());
                    double hpMaxima = Double.parseDouble(campos[4].trim());
                    String tipo = campos[5].trim();
                    String habitat = campos[6].trim();
                    Sexo sexo = randomSexo();
                    String dieta = " ";

                    // Llamar al servicio para crear el dinosaurio
                    dinosaurioService.crearDinosaurio(tipo, especie, 0, alturaMaxima, pesoMaximo, sexo, hpMaxima, false, FaseCicloDeVida.HUEVO, habitat, dieta);

                } catch (NumberFormatException e) {
                    System.err.println("Error en formato numérico en la línea: " + linea);
                    e.printStackTrace();
                } catch (Exception e) {
                    System.err.println("Error al procesar la línea: " + linea);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + rutaCSV);
            e.printStackTrace();
        }
    }
    private Sexo randomSexo() {
        return new Random().nextBoolean() ? Sexo.MACHO : Sexo.HEMBRA;
    }

    public String getAllDinosauriosAsJSON() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(dinosaurioFactory);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public List<Dinos> getDinosauriosPorHabitatYDieta(String habitat, String dieta) {
        return dinosaurioRepository.findAll().stream()
                .filter(dino -> dino.getHabitat().equalsIgnoreCase(habitat))
                .filter(dino -> dino.getTipo().equalsIgnoreCase(dieta))
                .collect(Collectors.toList());
    }

}
