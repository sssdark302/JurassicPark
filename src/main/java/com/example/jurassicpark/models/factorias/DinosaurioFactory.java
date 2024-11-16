package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.ciclodevida.GestorCV;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DinosaurioFactory {

    @Autowired
    @Lazy
    GestorCV gestorCV;

    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, String habitat, String dieta) {
        if (!esHabitatCompatible(habitat, tipo)) {
            throw new IllegalArgumentException("El dinosaurio no es compatible con el hábitat proporcionado: " + habitat);
        }
        Dinos dino = new Dinos(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, tuvoHijos, faseCicloDeVida, habitat, tipo, dieta);
        gestorCV.iniciarCiclo(dino);
        return dino;
    }

    private boolean esHabitatCompatible(String habitat, String tipo) {
        switch (habitat) {
            case "Acuatico":
                return tipo.equals("Carnivoro") || tipo.equals("Omnivoro");
            case "Terrestre":
                return tipo.equals("Carnivoro") || tipo.equals("Herbivoro") || tipo.equals("Omnivoro");
            case "Aereo":
                return tipo.equals("Carnivoro") || tipo.equals("Omnivoro");
            default:
                return false;
        }
    }
}
