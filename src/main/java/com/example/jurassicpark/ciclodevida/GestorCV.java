package com.example.jurassicpark.ciclodevida;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioInstalacionService;
import com.example.jurassicpark.service.DinosaurioService;
import jakarta.annotation.PreDestroy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class GestorCV implements CiclodeVida {

    private Map<Integer, FaseCicloDeVida> fasesDinosaurios = new ConcurrentHashMap<>();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    @Autowired
    @Lazy
    private DinosaurioFactory dinosaurioFactory;

    @Autowired
    @Lazy
    private DinosaurioService dinosaurioService;

    @Autowired
    @Lazy
    private DinosaurioInstalacionRepository dinosaurioInstalacionRepository;

    @Autowired
    @Lazy
    private DinosaurioInstalacionService dinosaurioInstalacionService;

    public FaseCicloDeVida obtenerFase(Dinos dinosaurio) {
        return fasesDinosaurios.getOrDefault(dinosaurio.getId(), FaseCicloDeVida.HUEVO);
    }

    public void iniciarCiclo(Dinos dinosaurio) {
        dinosaurio.setEdad(0);
        dinosaurio.setAlturamaxima(dinosaurio.getAlturamaximaOriginal() * 0); // Por ejemplo, 25% del original
        dinosaurio.setPesomaximo((int) (dinosaurio.getPesomaximoOriginal() * 0));
        dinosaurio.setHpmaxima(dinosaurio.getHpmaximaOriginal() * 0);
        fasesDinosaurios.put(dinosaurio.getId(), FaseCicloDeVida.HUEVO);
        System.out.println("Iniciando ciclo de vida para " + dinosaurio.getEspecie() + "...");
        scheduler.scheduleAtFixedRate(() -> avanzarFase(dinosaurio), 0, 2, TimeUnit.SECONDS);
    }


    private void avanzarFase(Dinos dinosaurio) {
        FaseCicloDeVida faseActual = fasesDinosaurios.get(dinosaurio.getId());
        if (faseActual == null) return; // El dinosaurio podría haber sido eliminado

        switch (faseActual) {
            case HUEVO -> {
                fasesDinosaurios.put(dinosaurio.getId(), FaseCicloDeVida.NACIMIENTO);
            }
            case NACIMIENTO -> {
                fasesDinosaurios.put(dinosaurio.getId(), FaseCicloDeVida.CRECIMIENTO);
                dinosaurio.setEdad(1);
                dinosaurio.setAlturamaxima(dinosaurio.getAlturamaximaOriginal() * 0.25);
                dinosaurio.setPesomaximo((int) (dinosaurio.getPesomaximoOriginal() * 0.25));
                dinosaurio.setHpmaxima(dinosaurio.getHpmaximaOriginal() * 0.25);
            }
            case CRECIMIENTO -> {
                fasesDinosaurios.put(dinosaurio.getId(), FaseCicloDeVida.REPRODUCCION);
                dinosaurio.setEdad(new Random().nextInt(5) + 5); // Edad entre 5 y 10
                dinosaurio.setAlturamaxima(dinosaurio.getAlturamaximaOriginal() * 0.75);
                dinosaurio.setPesomaximo((int) (dinosaurio.getPesomaximoOriginal() * 0.75));
                dinosaurio.setHpmaxima(dinosaurio.getHpmaximaOriginal() * 0.75);
            }
            case REPRODUCCION -> {
                System.out.println("El dinosaurio " + dinosaurio.getEspecie() + " está en la fase de reproducción.");
                fasesDinosaurios.put(dinosaurio.getId(), FaseCicloDeVida.ADULTO);
            }
            case ADULTO -> {
                fasesDinosaurios.put(dinosaurio.getId(), FaseCicloDeVida.MUERTE);
            }
            case MUERTE -> TerminarCiclo(dinosaurio);
        }
    }


    public void TerminarCiclo(Dinos dinosaurio) {
        fasesDinosaurios.remove(dinosaurio.getId());
        dinosaurioService.eliminarDinosaurio(dinosaurio);
    }

    public boolean verificarReproduccion(Dinos dinosaurio1, Dinos dinosaurio2) {
        return obtenerFase((Dinos) dinosaurio1) == FaseCicloDeVida.REPRODUCCION &&
                obtenerFase((Dinos) dinosaurio2) == FaseCicloDeVida.REPRODUCCION &&
                dinosaurio1.getSexo() != dinosaurio2.getSexo() &&
                dinosaurio1.getEspecie().equals(dinosaurio2.getEspecie()) &&
                !dinosaurio1.isTuvoHijos() && !dinosaurio2.isTuvoHijos();
    }

    private void buscarParejaYDarloOportunidadDeReproduccion(Dinos dinosaurio) {
        System.out.println("Buscando pareja para " + dinosaurio.getEspecie() + "...");

        // Obtener la instalación del dinosaurio desde el repositorio
        DinosaurioInstalaciones relacionActual = dinosaurioInstalacionRepository.findByDinosaurio(dinosaurio)
                .orElseThrow(() -> new IllegalArgumentException("El dinosaurio no está asignado a ninguna instalación"));

        InstalacionE instalacion = relacionActual.getInstalacion();

        // Filtrar los candidatos dentro de la misma instalación
        List<Dinos> candidatos = dinosaurioInstalacionRepository.findByInstalacion(instalacion)
                .stream()
                .map(DinosaurioInstalaciones::getDinosaurio)
                .filter(d -> !d.equals(dinosaurio) && verificarReproduccion(dinosaurio, d))
                .collect(Collectors.toList());

        if (candidatos.isEmpty()) {
            System.out.println("No se encontró pareja compatible para " + dinosaurio.getEspecie() + ".");
            return;
        }

        // Intentar reproducción con el primer candidato compatible
        intentarReproduccion(dinosaurio, candidatos.get(0));
    }


    public void intentarReproduccion(Dinos dinosaurio1, Dinos dinosaurio2) {
        if (verificarReproduccion(dinosaurio1, dinosaurio2)) {
            double probabilidadRepro = (Math.random() * 100) + 1;
            if (probabilidadRepro >= 29) {
                reproducirse(dinosaurio1, dinosaurio2);
                dinosaurio1.setTuvoHijos(true);
                dinosaurio2.setTuvoHijos(true);
            } else {
                System.out.println("No se ha podido realizar la reproducción debido a baja probabilidad.");
            }
        } else {
            System.out.println("Los dinosaurios no cumplen con las condiciones para reproducirse.");
        }
    }

    public void reproducirse(Dinos dinosaurio1, Dinos dinosaurio2) {
        System.out.println("Reproduciendo dinosaurios " + dinosaurio1.getEspecie() + " y " + dinosaurio2.getEspecie() + "...");

        Sexo sexo = new Random().nextBoolean() ? Sexo.MACHO : Sexo.HEMBRA;

        Dinos nuevoDino = dinosaurioFactory.crearDinosaurio(
             dinosaurio1.getTipo(),
                dinosaurio1.getEspecie(),
                0, // Edad
                0, //altura
                0, //peso
                sexo,
                0, false,
                FaseCicloDeVida.HUEVO,
                dinosaurio1.getAlturamaximaOriginal(),
                dinosaurio1.getPesomaximoOriginal(),
                dinosaurio2.getHpmaximaOriginal()//hp
        );

        // Obtener la instalación del primer padre
        DinosaurioInstalaciones relacionPadre = dinosaurioInstalacionRepository.findByDinosaurio(dinosaurio1)
                .orElseThrow(() -> new IllegalArgumentException("El dinosaurio no está asignado a ninguna instalación"));

        // Asignar el nuevo dinosaurio a la misma instalación
        dinosaurioInstalacionService.guardarRelacionDinosaurioInstalacion(nuevoDino, relacionPadre.getInstalacion());
        System.out.printf("Dinosaurio %s creado con éxito en fase HUEVO.\n", nuevoDino.getEspecie());
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(120, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void huevo() {
        System.out.println("Se está desarrollando un huevo.");
    }

    @Override
    public void nacer() {
        System.out.println("El dinosaurio ha nacido.");
    }

    @Override
    public void crecer() {
        System.out.println("El dinosaurio está creciendo.");
    }

    @Override
    public void adulto() {
        System.out.println("El dinosaurio ha alcanzado la adultez.");
    }

    @Override
    public void reproducirse() {
        System.out.println("El dinosaurio se está reproduciendo.");
    }

    @Override
    public void morir() {
        System.out.println("El dinosaurio ha muerto.");
    }
}
