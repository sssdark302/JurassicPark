package com.example.jurassicpark.models.entidades;

import com.example.jurassicpark.models.Instalacion;
import jakarta.persistence.*;

@Entity
@Table(name = "instalaciones")
public class InstalacionE extends Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public InstalacionE(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        super(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
    }

    public InstalacionE() {
        super(null, 0, null, 0, null, null, 0, null, null, null);
    }
}
