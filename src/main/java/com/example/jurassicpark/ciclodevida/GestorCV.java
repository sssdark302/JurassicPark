package com.example.jurassicpark.ciclodevida;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.repository.DinosaurioRepository;
import com.example.jurassicpark.service.DinosaurioService;
import jakarta.annotation.PreDestroy;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Service
public class GestorCV implements CiclodeVida {

    //intentar meter las fases de los dinos en una bdd que se relacione con el id, para que no se pierda la info
    private Map<Dinosaurio, FaseCicloDeVida> fasesDinosaurios = new HashMap<>();
    Scanner scanner = new Scanner(System.in);
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private DinosaurioFactory dinosaurioFactory;

    @Autowired
    private DinosaurioService dinosaurioService;

    @Autowired
    private DinosaurioRepository dinosaurioRepository;


    public FaseCicloDeVida obtenerFase(Dinos dinosaurio) {
        return fasesDinosaurios.getOrDefault(dinosaurio, FaseCicloDeVida.HUEVO);
    }

    public void iniciarCiclo(Dinos dinosaurio) {
        dinosaurio.setEdad(0);
        dinosaurio.setAltura_maxima(0);
        dinosaurio.setPeso_maximo(0);
        dinosaurio.setHp_maxima(0);
        fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.HUEVO);
        System.out.println("Iniciando ciclo de vida para " + dinosaurio.getEspecie() + "...");
        executorService.submit(() -> ejecutarCicloDeVida(dinosaurio));
    }

    private void ejecutarCicloDeVida(Dinos dinosaurio) {
        try {
            while (fasesDinosaurios.get(dinosaurio) != FaseCicloDeVida.MUERTE && !Thread.currentThread().isInterrupted()) {
                avanzarFase(dinosaurio);
                Thread.sleep(2000); // Simulación de espera entre fases
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            TerminarCiclo(dinosaurio);
        }
    }

    public void avanzarFase(Dinos dinosaurio) {
        FaseCicloDeVida faseActual = fasesDinosaurios.get(dinosaurio);

        switch (faseActual) {
            case HUEVO -> {
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.NACIMIENTO);
            }
            case NACIMIENTO -> {
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.CRECIMIENTO);
                dinosaurio.setEdad(1);
                dinosaurio.setAltura_maxima(dinosaurio.getAltura_maxima() * 0.25);
                dinosaurio.setPeso_maximo((int) (dinosaurio.getPeso_maximo() * 0.25));
                dinosaurio.setHp_maxima(dinosaurio.getHp_maxima() * 0.25);
            }
            case CRECIMIENTO -> {
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.REPRODUCCION);
                dinosaurio.setEdad( new Random().nextInt(5-9));
                dinosaurio.setAltura_maxima(dinosaurio.getAltura_maxima() * 0.75);
                dinosaurio.setPeso_maximo((int) (dinosaurio.getPeso_maximo() * 0.75));
                dinosaurio.setHp_maxima(dinosaurio.getHp_maxima() * 0.75);
            }
            case REPRODUCCION -> {
                System.out.println("El dinosaurio " + dinosaurio.getEspecie() + " está en la fase de reproducción.");
                dinosaurio.setEdad( new Random().nextInt(10-20));
                dinosaurio.setAltura_maxima(dinosaurio.getAltura_maxima() * 0.75);
                dinosaurio.setPeso_maximo((int) (dinosaurio.getPeso_maximo() * 0.75));
                dinosaurio.setHp_maxima(dinosaurio.getHp_maxima() * 0.75);

                buscarParejaYDarloOportunidadDeReproduccion(dinosaurio);
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.ADULTO);
            }
            case ADULTO -> {
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.MUERTE);
            }
            case MUERTE -> TerminarCiclo(dinosaurio);
        }
    }

    public void TerminarCiclo(Dinos dinosaurio) {
        fasesDinosaurios.remove(dinosaurio);
        dinosaurioService.eliminarDinosaurio(dinosaurio);
    }

    public boolean verificarReproduccion(Dinosaurio dinosaurio1, Dinosaurio dinosaurio2) {
        return obtenerFase((Dinos) dinosaurio1) == FaseCicloDeVida.REPRODUCCION &&
                obtenerFase((Dinos) dinosaurio2) == FaseCicloDeVida.REPRODUCCION &&
                dinosaurio1.getSexo() != dinosaurio2.getSexo() &&
                dinosaurio1.getEspecie().equals(dinosaurio2.getEspecie()) &&
                !dinosaurio1.getTuvoHijos() && !dinosaurio2.getTuvoHijos();
    }
    private void buscarParejaYDarloOportunidadDeReproduccion(Dinosaurio dinosaurio) {
        System.out.println("Buscando pareja para " + dinosaurio.getEspecie() + "...");

        //SE BUSCA EN LA INSTALACION EN LA QUE ESTA EL DINOSAURIO
        List<Dinos> candidatos = dinosaurioService.buscarDinosauriosPorFaseYEspecie(FaseCicloDeVida.REPRODUCCION, dinosaurio.getEspecie());

        for (Dinos candidato : candidatos) {
            // Evitar reproducirse con uno mismo
            if (candidato.equals(dinosaurio)) {
                continue;
            }

            // Verificar condiciones de reproducción
            if (verificarReproduccion(dinosaurio, candidato)) {
                intentarReproduccion(dinosaurio, candidato);
                return; // Salir después de encontrar una pareja compatible
            }
        }

        System.out.println("No se encontró pareja compatible para " + dinosaurio.getEspecie() + ".");
    }

    public void intentarReproduccion(Dinosaurio dinosaurio1, Dinosaurio dinosaurio2) {
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
            System.out.println("Los dinosaurios no cumplen con las condiciones para reproducirse. Asegúrate de que ambos estén en fase REPRODUCCION.");
        }
    }

    @SneakyThrows
    public void reproducirse(Dinosaurio dinosaurio1, Dinosaurio dinosaurio2) {
        System.out.println("Reproduciendo dinosaurios " + dinosaurio1.getEspecie() + " y " + dinosaurio2.getEspecie() + "...");
        Sexo sexo = new Random().nextBoolean() ? Sexo.MACHO : Sexo.HEMBRA;

        Dinosaurio nuevoDino = dinosaurioFactory.crearDinosaurio(
                dinosaurio1.getTipo(),
                dinosaurio1.getEspecie(),
                0,  // Edad inicial
                0,  // Altura inicial
                0,  // Peso inicial
                sexo,
                0,  // HP inicial
                false,  // Tuvo hijos
                FaseCicloDeVida.HUEVO,
                dinosaurio1.getHabitat(),
                dinosaurio1.getDieta()
        );
        dinosaurioRepository.save((Dinos) nuevoDino);
        System.out.printf("Dinosaurio %s creado con éxito en fase HUEVO.\n", nuevoDino.getEspecie());
    }

    @PreDestroy
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void huevo() {
        System.out.println("Se esta desarrollando un huevo.");
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

