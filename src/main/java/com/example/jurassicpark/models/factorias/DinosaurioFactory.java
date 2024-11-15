package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.stereotype.Component;

@Component
public class DinosaurioFactory {

    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, String habitat) {
        if (!esHabitatCompatible(habitat, tipo)) {
            throw new IllegalArgumentException("El dinosaurio no es compatible con el habitat proporcionado");
        }

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
