package com.example.jurassicpark.models.factorias;
import com.example.jurassicpark.models.entidades.InstalacionE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InstalacionFactory {

    @Autowired
    @Lazy
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public InstalacionE crearInstalacion(String nombre, int capacidad, double terreno, String seguridad,
                                         String descripcion, int personal, String horario, String tipo) {
        InstalacionE nuevaInstalacion;

        if ("Dinosaurios_Plantas".equals(tipo)) {
            nuevaInstalacion = dinosauriosPlantasFactory.crearInstalacionDinosauriosPlantas(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
        } else {
            nuevaInstalacion = new InstalacionE(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
        }

        // Guardar en tabla temporal
        guardarEnTablaTemporal(nuevaInstalacion);

        return nuevaInstalacion;
    }

    private void guardarEnTablaTemporal(InstalacionE instalacion) {
        jdbcTemplate.update("""
            INSERT INTO temp_instalaciones (nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """, instalacion.getNombre(), instalacion.getCapacidad(), instalacion.getTerreno(),
                instalacion.getSeguridad(), instalacion.getDescripcion(), instalacion.getPersonal(),
                instalacion.getHorario(), instalacion.getTipo());
    }
}
