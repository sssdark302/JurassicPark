package com.example.jurassicpark.ciclodevida;

import com.example.jurassicpark.exceptiones.DinosaurioNotFoundException;
import com.example.jurassicpark.exceptiones.SexoDinosaurioNotFoundException;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.Sexo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.jurassicpark.ciclodevida.FaseCicloDeVida.*;

public class GestorCV implements CiclodeVida {
    private Map<Dinosaurio, FaseCicloDeVida> fasesDinosaurios = new HashMap<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    Scanner scanner = new Scanner(System.in);

    public FaseCicloDeVida obtenerFase(Dinosaurio dinosaurio) {
        return fasesDinosaurios.getOrDefault(dinosaurio, HUEVO); //por default empieza como huevo
    }

    public void iniciarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.put(dinosaurio, HUEVO);
        executorService.submit(() -> ejecutarCicloDeVida(dinosaurio));
    }
    private void ejecutarCicloDeVida(Dinosaurio dinosaurio) {
        while (fasesDinosaurios.get(dinosaurio) != FaseCicloDeVida.MUERTE) {
            avanzarFase(dinosaurio);
        }
        TerminarCiclo(dinosaurio);
    }

    public void avanzarFase(Dinosaurio dinosaurio) {
        FaseCicloDeVida faseActual = obtenerFase(dinosaurio);//hacer avanzar fase cada x tiempo
        long tiempoEspera = obtenerTiempoEsperaPorFase(faseActual);

        try {
            System.out.println("El dinosaurio " + dinosaurio.getEspecie() + " está en fase " + faseActual + ". Esperando " + (tiempoEspera / 1000) + " segundos...");
            Thread.sleep(tiempoEspera); // Espera en milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (faseActual) {
            case HUEVO:
                fasesDinosaurios.put(dinosaurio, NACIMIENTO);
                nacer();
                break;
            case NACIMIENTO:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.CRECIMIENTO);
                crecer();
                break;
            case CRECIMIENTO:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.REPRODUCCION);
                break;
            case REPRODUCCION:
                reproducirse();
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.ADULTO);
                break;
            case ADULTO:
                fasesDinosaurios.put(dinosaurio, MUERTE);
                morir();
                break;
            case MUERTE:
                TerminarCiclo(dinosaurio);
                break;
        }
    }

    public void verificarReproduccion(Dinosaurio dinosaurio, Dinosaurio dinosaurio2) { //comprueba si se puede reproducir
        //si ambos dinos de la misma especie estan en fase de repro, son sexos opuestos, y no se han reproducido antes, se reproducen
        if (obtenerFase(dinosaurio) == FaseCicloDeVida.REPRODUCCION && obtenerFase(dinosaurio2) == FaseCicloDeVida.REPRODUCCION) {
            if (dinosaurio.getSexo() != dinosaurio2.getSexo()) {
                if (dinosaurio.getEspecie().equals(dinosaurio2.getEspecie())) {
                    if (!dinosaurio.getTuvoHijos() && !dinosaurio2.getTuvoHijos()) {
                        reproducirse();
                    }
                }
            }
        }
    }
    public void reproducirse(Dinosaurio dinosaurio) {
        if (dinosaurio.getSexo() == Sexo.MACHO) {
            System.out.println("El macho " + dinosaurio + " está listo para reproducirse.");
            reproducirse();
            if (dinosaurio.getSexo() == Sexo.HEMBRA) {
                System.out.println("La hembra " + dinosaurio + " está lista para reproducirse.");
                reproducirse();
            }
            else {
                throw new SexoDinosaurioNotFoundException("El dinosaurio no tiene sexo.");
            }
        }
    }

    /*
    public boolean seReproducio(Dinosaurio dinosaurio){
        if (obtenerFase(dinosaurio) == FaseCicloDeVida.ADULTO){
            return true;
        } else {
            return false;
        }
    }

     */
    private long obtenerTiempoEsperaPorFase(FaseCicloDeVida fase) {
        switch (fase) {
            case HUEVO:
                return 5000;
            case NACIMIENTO:
                return 10000;
            case CRECIMIENTO:
                return 15000;
            case REPRODUCCION:
                return 20000;
            case ADULTO:
                return 10000;
            case MUERTE:
                return 5000;
            default:
                return 0;
        }
    }

    public void acelerarFase(){
        //acelera el tiempo en una fase
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

