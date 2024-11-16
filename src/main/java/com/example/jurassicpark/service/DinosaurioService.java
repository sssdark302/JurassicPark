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

    @Autowired
    @Lazy
    private DinosaurioDataStore dinosaurioDataStore;


    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo,
                                 Sexo sexo, double hpMaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida) {
        // Crear dinosaurio usando la fábrica
        Dinos dinosaurio = dinosaurioFactory.crearDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo,
                sexo, hpMaxima, tuvoHijos, faseCicloDeVida);

        // Guardar en la base de datos
        Dinos dinosaurioGuardado = dinosaurioRepository.save(dinosaurio);

        // Actualizar el DataStore
        dinosaurioDataStore.agregarDinosaurio(dinosaurioGuardado);

        return dinosaurioGuardado;
    }

    public List<Dinos> listarDinosaurios() {
        return dinosaurioRepository.findAll();
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
        // Eliminar de la base de datos
        dinosaurioRepository.delete(dinosaurio);

        // Actualizar el DataStore
        dinosaurioDataStore.eliminarDinosaurio(dinosaurio);
    }
    public void cargarDatosCSV(String rutaCSV) {
        int lineasProcesadas = 0;
        int errores = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea;
            String encabezado = br.readLine(); // Leer encabezado y descartarlo
            if (encabezado == null || encabezado.isEmpty()) {
                throw new IOException("El archivo CSV está vacío o no tiene encabezado.");
            }

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length < 7) {
                    System.err.println("Línea inválida en CSV (menos de 7 campos): " + linea);
                    errores++;
                    continue;
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
                    String dieta = "Desconocida";

                    crearDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima,
                            false, FaseCicloDeVida.HUEVO);

                    lineasProcesadas++;
                } catch (Exception e) {
                    System.err.println("Error al procesar la línea: " + linea);
                    e.printStackTrace();
                    errores++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + rutaCSV);
            e.printStackTrace();
        }
        System.out.println("Carga completada. Líneas procesadas: " + lineasProcesadas + ", Errores: " + errores);
    }

    private Sexo randomSexo() {
        return Math.random() < 0.5 ? Sexo.MACHO : Sexo.HEMBRA;
    }
}
