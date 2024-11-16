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
    private GestorCV gestorCV;

    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturamaxima, int pesomaximo,
                                 Sexo sexo, double hpmaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida) {
        // Crear el dinosaurio con los atributos básicos
        Dinos dino = new Dinos(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, tipo, faseCicloDeVida, tuvoHijos);

        // Iniciar el ciclo de vida para el dinosaurio recién creado
        gestorCV.iniciarCiclo(dino);

        return dino;
    }
}
