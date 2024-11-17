package com.example.jurassicpark.models.entidades;

import com.example.jurassicpark.models.Instalacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "instalaciones")
public class InstalacionE extends Instalacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "dinosaurios_instalaciones", // Nombre de la tabla de unión
            joinColumns = @JoinColumn(name = "instalacion_id"), // FK a InstalacionE
            inverseJoinColumns = @JoinColumn(name = "dinosaurio_id") // FK a Dinos
    )
    private Set<Dinos> dinosaurios = new HashSet<>();

    public InstalacionE(String nombre, int capacidad, double terreno, String seguridad, String descripcion, int personal, String horario, String tipo) {
        super(nombre, capacidad, terreno, seguridad, descripcion, personal, horario, tipo);
    }

    public InstalacionE() {
        super();
    }
}
