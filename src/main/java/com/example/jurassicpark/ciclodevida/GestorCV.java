package com.example.jurassicpark.ciclodevida;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.DinosaurioDataStore;
import com.example.jurassicpark.models.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.service.DinosaurioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.jurassicpark.ciclodevida.FaseCicloDeVida.*;
import static com.example.jurassicpark.ciclodevida.FaseCicloDeVida.HUEVO;

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


    public FaseCicloDeVida obtenerFase(Dinosaurio dinosaurio) {
        return fasesDinosaurios.getOrDefault(dinosaurio, HUEVO); //por default empieza como huevo
    }

    public void iniciarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.put(dinosaurio, HUEVO);
        System.out.println("Quiere iniciar el ciclo de vida de un dinosaurio? (s/n)");
        String respuesta = scanner.nextLine();
        if (respuesta.equalsIgnoreCase("s")){
            System.out.println("Escoja la especie del dinosaurio: ");
            //meter la lista de dinos
            String especie = scanner.nextLine();
            System.out.println("Iniciando ciclo de vida para el dinosaurio " + dinosaurio.getEspecie() + "...");
            executorService.submit(() -> ejecutarCicloDeVida(dinosaurio));
        }
        else {
            System.out.println("Ciclo de vida no iniciado.");
        }
    }

    private void ejecutarCicloDeVida(Dinosaurio dinosaurio) {
        Thread hilo = new Thread(() -> {
            try {
                while (fasesDinosaurios.get(dinosaurio) != MUERTE && !Thread.currentThread().isInterrupted()) {
                    System.out.println("¿Desea avanzar la fase del dinosaurio " + dinosaurio.getEspecie() + "? (s/n)");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equalsIgnoreCase("s")) {
                        avanzarFase(dinosaurio);
                    } else {
                        System.out.println("Esperando para avanzar a la siguiente fase...");
                        try {
                            Thread.sleep(2000); // espera antes de volver a preguntar
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // re-interrupt the thread
                        }
                    }
                }
            } finally {
                TerminarCiclo(dinosaurio);
            }
        });
        hilo.start();
    }

    public void avanzarFase(Dinosaurio dinosaurio) {
        FaseCicloDeVida faseActual = obtenerFase(dinosaurio);
        long tiempoEspera = obtenerTiempoEsperaPorFase(faseActual);
        try {
            System.out.println("El dinosaurio " + dinosaurio.getEspecie() + " está en fase " + faseActual + ". Esperando " + (tiempoEspera / 1000) + " segundos...");
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch (faseActual) {
            case HUEVO -> {
                fasesDinosaurios.put(dinosaurio, NACIMIENTO);
                nacer();
            }
            case NACIMIENTO -> {
                fasesDinosaurios.put(dinosaurio, CRECIMIENTO);
                crecer();
            }
            case CRECIMIENTO -> {
                fasesDinosaurios.put(dinosaurio, REPRODUCCION);
                adulto();
            }
            case REPRODUCCION -> {
                fasesDinosaurios.put(dinosaurio, ADULTO);
                reproducirse();
            }
            case ADULTO -> {
                fasesDinosaurios.put(dinosaurio, MUERTE);
                morir();
            }
            case MUERTE -> TerminarCiclo(dinosaurio);
        }
    }


    @SneakyThrows
    public void verificarReproduccion(Dinosaurio dinosaurio, Dinosaurio dinosaurio2) { //si ambos dinos estan en etapa de repro, misma especie, distinto sexo y no han tenido hijos
        double probabilidadRepro = (Math.random()* (1-100))+ 1;
        if (obtenerFase(dinosaurio) == REPRODUCCION
                && obtenerFase(dinosaurio2) == REPRODUCCION
                && dinosaurio.getSexo() != dinosaurio2.getSexo()
                && dinosaurio.getEspecie().equals(dinosaurio2.getEspecie())
                && !dinosaurio.getTuvoHijos() && !dinosaurio2.getTuvoHijos()) {
            System.out.println("Condiciones para la reproduccion cumplidas.");
            Thread.sleep(1000);
            if (probabilidadRepro < 29) {
                System.out.println("No se ha podido realizar la reproducción.");
            }
            else {
                reproducirse(dinosaurio, dinosaurio2);
                String especieNuevoDino = dinosaurio.getEspecie();
                int edadNuevoDino = 0;
                double alturaMaximaNuevoDino = 0;
                int pesoMaximoNuevoDino = 0;
                Sexo sexoNuevoDino = new Random().nextBoolean() ? Sexo.MACHO : Sexo.HEMBRA;
                double hpMaximaNuevoDino = 0;
                boolean tuvoHijosNuevoDino = false;
                String tipoDino = dinosaurio.getTipo();
                FaseCicloDeVida faseCicloDeVidaND = HUEVO;
                Dinos nuevoDino = dinosaurioService.agregarDinosaurio(tipoDino, especieNuevoDino, edadNuevoDino, alturaMaximaNuevoDino, pesoMaximoNuevoDino, sexoNuevoDino, hpMaximaNuevoDino, tuvoHijosNuevoDino, faseCicloDeVidaND);
                System.out.println("¡Nuevo dinosaurio en fase huevo creado: " + nuevoDino + "!");
                dinosaurio.setTuvoHijos(true);
                dinosaurio2.setTuvoHijos(true);
            }
        } else {
            System.out.println("Los dinosaurios no cumplen con las condiciones para reproducirse.");
        }
    }
    @SneakyThrows
    public void reproducirse(Dinosaurio dinosaurio1, Dinosaurio dinosaurio2) {
        verificarReproduccion(dinosaurio1, dinosaurio2);
    }

    private long obtenerTiempoEsperaPorFase(FaseCicloDeVida fase) {
        return switch (fase) {
            case HUEVO -> 5000;
            case NACIMIENTO -> 10000;
            case CRECIMIENTO -> 15000;
            case REPRODUCCION -> 20000;
            case ADULTO -> 10000;
            case MUERTE -> 5000;
        };
    }

    public void TerminarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.remove(dinosaurio);
        System.out.println("El " + dinosaurio + " ha completado su ciclo de vida.");
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

