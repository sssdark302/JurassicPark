package com.example.jurassicpark.service;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.repository.DinosaurioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosaurioService {

    @Autowired
    @Lazy
    private DinosaurioRepository dinosaurioRepository;

    @Autowired
    @Lazy
    private DinosaurioFactory dinosaurioFactory;

    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo,
                                 Sexo sexo, double hpMaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, double alturamaximaOriginal, double pesomaximoOriginal, double hpmaximaOriginal) {
        // Crear dinosaurio usando la fábrica
        Dinos dinosaurio = dinosaurioFactory.crearDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo,
                sexo, hpMaxima, tuvoHijos, faseCicloDeVida, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
        // Guardar en la base de datos
        Dinos dinosaurioGuardado = dinosaurioRepository.save(dinosaurio);

        return dinosaurioGuardado;
    }

    public List<Dinos> listarDinosaurios() {
        List<Dinos> dinosaurios = dinosaurioRepository.findAll();
        for (Dinos dino : dinosaurios) {
            System.out.println("Dino: " + dino.getEspecie() +
                    ", Altura Original: " + dino.getAlturamaximaOriginal() +
                    ", Peso Original: " + dino.getPesomaximoOriginal() +
                    ", HP Original: " + dino.getHpmaximaOriginal());
        }
        return dinosaurios;
    }


    public void eliminarDinosaurioPorId(int id) {
        if (dinosaurioRepository.existsById(id)) {
            dinosaurioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Dinosaurio con ID " + id + " no encontrado.");
        }
    }

    public List<Dinos> buscarDinosauriosPorFaseYEspecie(FaseCicloDeVida faseCicloDeVida, String especie) {
        return dinosaurioRepository.findByFaseCicloDeVidaAndEspecie(faseCicloDeVida, especie);
    }

    public void eliminarDinosaurio(Dinos dinosaurio) {
        dinosaurioRepository.delete(dinosaurio);
    }

    public void cargarDatosCSV(String rutaCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length < 7) {
                    System.err.println("Línea inválida: " + linea);
                    continue;
                }

                Dinos nuevoDino = new Dinos();
                nuevoDino.setEspecie(campos[0].trim());
                nuevoDino.setAlturamaximaOriginal(Double.parseDouble(campos[2].trim()));
                nuevoDino.setPesomaximoOriginal(Integer.parseInt(campos[3].trim()));
                nuevoDino.setHpmaximaOriginal(Double.parseDouble(campos[4].trim()));
                nuevoDino.setAlturamaxima(0.0);
                nuevoDino.setPesomaximo(0);
                nuevoDino.setHpmaxima(0.0);
                nuevoDino.setTipo(campos[5].trim());
                nuevoDino.setSexo(randomSexo());
                nuevoDino.setFaseCicloDeVida(FaseCicloDeVida.HUEVO);

                dinosaurioRepository.save(nuevoDino); // Guardar en la base
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }


    private Sexo randomSexo() {
        return Math.random() < 0.5 ? Sexo.MACHO : Sexo.HEMBRA;
    }
}
