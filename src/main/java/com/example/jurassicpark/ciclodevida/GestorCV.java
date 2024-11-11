package com.example.jurassicpark.ciclodevida;

import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.Sexo;

import java.util.HashMap;
import java.util.Map;

public class GestorCV implements CiclodeVida {
    private Map<Dinosaurio, FaseCicloDeVida> fasesDinosaurios = new HashMap<>();

    public FaseCicloDeVida obtenerFase(Dinosaurio dinosaurio) {
        return fasesDinosaurios.getOrDefault(dinosaurio, FaseCicloDeVida.NACIMIENTO);
    }

    public void iniciarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.HUEVO);
        huevo();
    }

    public void avanzarFase(Dinosaurio dinosaurio) {
        FaseCicloDeVida faseActual = obtenerFase(dinosaurio);

        switch (faseActual) {
            case HUEVO:
                fasesDinosaurios.put(dinosaurio, FaseCicloDeVida.NACIMIENTO);
                nacer();
                break;
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
                if (dinosaurio.getSexo() == Sexo.MACHO) {
                    System.out.println("El macho " + dinosaurio + " está listo para reproducirse.");
                    reproducirse();
                }
                if (dinosaurio.getSexo() == Sexo.HEMBRA) {
                    System.out.println("La hembra " + dinosaurio + " está lista para reproducirse.");
                    reproducirse();
                }
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

    public void verificarReproduccion(Dinosaurio dinosaurio, Dinosaurio dinosaurio2) {
       //si ambos dinos de la misma especie estan en fase de repro, y son sexos opuestos, se reproducen
        if (obtenerFase(dinosaurio) == FaseCicloDeVida.REPRODUCCION && obtenerFase(dinosaurio2) == FaseCicloDeVida.REPRODUCCION) {
            if (dinosaurio.getSexo() != dinosaurio2.getSexo()) {
                if (dinosaurio.getEspecie().equals(dinosaurio2.getEspecie())) {
                    reproducirse();
                }
            }
        }
    }

    public void TerminarCiclo(Dinosaurio dinosaurio) {
        fasesDinosaurios.remove(dinosaurio);
        System.out.println(dinosaurio + " ha completado su ciclo de vida.");
    }

    @Override
    public void huevo() {
        System.out.println("Se esta desarrolando un huevo.");
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

