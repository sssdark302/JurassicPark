package com.example.jurassicpark.models;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.stereotype.Component;

@Component
public class DinosaurioFactory {
  public Dinos crearDinosaurio(String tipo, String especie, int edad, double altura_maxima, int peso_maximo, Sexo sexo, double hp_maxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida) {
      switch (tipo) {
          case "Carnivoro":
              return new Carnivoro(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos, faseCicloDeVida);
          case "Herbivoro":
              return new Herbivoro(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos, faseCicloDeVida);
          case "Omnivoro":
              return new Omnivoro(especie, edad, altura_maxima, peso_maximo, sexo, hp_maxima, tuvoHijos, faseCicloDeVida);
          default:
              throw new IllegalArgumentException("Tipo de dinosaurio desconocido: " + tipo);
      }
  }
}

