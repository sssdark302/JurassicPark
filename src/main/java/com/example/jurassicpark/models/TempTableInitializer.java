package com.example.jurassicpark.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;

public class TempTableInitializer implements CommandLineRunner {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Crear tabla temporal para dinosaurios
        jdbcTemplate.execute("DROP TABLE IF EXISTS temp_dinosaurios;");
        jdbcTemplate.execute("""
            CREATE TEMP TABLE temp_dinosaurios (
                id SERIAL PRIMARY KEY,
                especie VARCHAR(255),
                edad INT,
                alturamaxima DOUBLE PRECISION,
                pesomaximo INT,
                hpmaxima DOUBLE PRECISION,
                tipo VARCHAR(255),
                sexo VARCHAR(10),
                tuvo_hijos BOOLEAN,
                fase_ciclo_de_vida VARCHAR(50)
            );
        """);

        // Crear tabla temporal para instalaciones
        jdbcTemplate.execute("DROP TABLE IF EXISTS temp_instalaciones;");
        jdbcTemplate.execute("""
            CREATE TEMP TABLE temp_instalaciones (
                id SERIAL PRIMARY KEY,
                nombre VARCHAR(255),
                capacidad INT,
                tipo VARCHAR(255),
                seguridad VARCHAR(255),
                descripcion TEXT,
                personal INT,
                horario VARCHAR(255),
                terreno DOUBLE PRECISION
            );
        """);
    }
}
