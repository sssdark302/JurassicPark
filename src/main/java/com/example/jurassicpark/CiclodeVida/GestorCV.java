package com.example.jurassicpark.CiclodeVida;

import com.example.jurassicpark.models.Dinosaurio;

import java.util.HashMap;
import java.util.Map;

public class GestorCV implements CiclodeVida {
    private Map<Dinosaurio, FaseCicloDeVida> fasesDinosaurios = new HashMap<>();

    public FaseCicloDeVida obtenerFase(Dinosaurio dinosaurio) {
        return fasesDinosaurios.getOrDefault(dinosaurio, FaseCicloDeVida.NACIMIENTO);
    }

    public void iniciarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.NACIMIENTO);
        nacer();
    }

    public void avanzarFase(Dinosaurio dinosaurio) {
        FaseCicloDeVida faseActual = obtenerFase(dinosaurio);

        switch (faseActual) {
            case NACIMIENTO:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.CRECIMIENTO);
                crecer();
                break;
            case CRECIMIENTO:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.ADULTO);
                adulto();
                break;
            case ADULTO:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.REPRODUCCION);
                verificarReproduccion(dinosaurio);
                break;
            case REPRODUCCION:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.MUERTE);
                morir();
                break;
            case MUERTE:
                TerminarCiclo(dinosaurio);
                break;
        }
    }

    public void verificarReproduccion(Dinosaurio dinosaurio) {
        if (fasesDinosaurios.get(dinosaurio) == FaseCicloDeVida.REPRODUCCION) {
            reproducirse();
        }
    }

    public void TerminarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.remove(dinosaurio);
        System.out.println(dinosaurio + " ha completado su ciclo de vida.");
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

