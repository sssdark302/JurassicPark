package com.example.jurassicpark.models.factorias;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.ciclodevida.GestorCV;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DinosaurioFactory {

    @Autowired
    @Lazy
    private GestorCV gestorCV;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturamaxima, int pesomaximo,
                                 Sexo sexo, double hpmaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida,
                                 double alturamaximaOriginal, double pesomaximoOriginal, double hpmaximaOriginal) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("El tipo de dinosaurio no puede ser nulo o vacío.");
        }

        // Crear dinosaurio basado en el tipo
        Dinos dino = switch (tipo.toLowerCase()) {
            case "carnivoro" -> crearCarnivoro(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, tuvoHijos, faseCicloDeVida, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
            case "herbivoro" -> crearHerbivoro(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, tuvoHijos, faseCicloDeVida, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
            case "omnivoro" -> crearOmnivoro(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, tuvoHijos, faseCicloDeVida, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
            default -> throw new IllegalArgumentException("Tipo de dinosaurio no válido: " + tipo);
        };

        // Iniciar el ciclo de vida del dinosaurio
        gestorCV.iniciarCiclo(dino);
        guardarEnTablaTemporal(dino);
        return dino;
    }

    private Dinos crearCarnivoro(String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima,
                                 boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, double alturamaximaOriginal,
                                 double pesomaximoOriginal, double hpmaximaOriginal) {
        return new Dinos(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, "Carnivoro", faseCicloDeVida, tuvoHijos, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
    }

    private Dinos crearHerbivoro(String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima,
                                 boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, double alturamaximaOriginal,
                                 double pesomaximoOriginal, double hpmaximaOriginal) {
        return new Dinos(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, "Herbivoro", faseCicloDeVida, tuvoHijos, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
    }

    private Dinos crearOmnivoro(String especie, int edad, double alturamaxima, int pesomaximo, Sexo sexo, double hpmaxima,
                                boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, double alturamaximaOriginal,
                                double pesomaximoOriginal, double hpmaximaOriginal) {
        return new Dinos(especie, edad, alturamaxima, pesomaximo, sexo, hpmaxima, "Omnivoro", faseCicloDeVida, tuvoHijos, alturamaximaOriginal, pesomaximoOriginal, hpmaximaOriginal);
    }

    private void guardarEnTablaTemporal(Dinos dinosaurio) {
        jdbcTemplate.update("""
            INSERT INTO temp_dinosaurios (especie, edad, alturamaxima, pesomaximo, hpmaxima, tipo, sexo, tuvo_hijos, fase_ciclo_de_vida)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """, dinosaurio.getEspecie(), dinosaurio.getEdad(), dinosaurio.getAlturamaxima(), dinosaurio.getPesomaximo(),
                dinosaurio.getHpmaxima(), dinosaurio.getTipo(), dinosaurio.getSexo().name(), dinosaurio.isTuvoHijos(),
                dinosaurio.getFaseCicloDeVida().name());
    }
}
