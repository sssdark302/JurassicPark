package com.example.jurassicpark.service;

import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosauriosPlantasFactory;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosaurioInstalacionService {

    @Autowired
    @Lazy
    private DinosaurioInstalacionRepository dinosaurioInstalacionRepository;

    @Autowired
    @Lazy
    private DinosauriosPlantasFactory dinosauriosPlantasFactory;

    private boolean puedenCoexistir(Dinos dino1, Dinos dino2) {
        if (dino1.getTipo().equals("Carnivoro") && dino2.getTipo().equals("Herbivoro")) {
            return false;
        }
        if (dino1.getTipo().equals("Herbivoro") && dino2.getTipo().equals("Carnivoro")) {
            return false;
        }
        return true;
    }

    public void asignarDinosaurioAInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionRepository.save(relacion);
        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    public void inicializarDinosauriosParaInstalacion(InstalacionE instalacion) {
        // Definir dinosaurios compatibles según el tipo de instalación
        List<Dinos> dinosauriosCompatibles = dinosauriosPlantasFactory.obtenerDinosauriosCompatiblesPorReglas(instalacion);

        // Definir el número de dinosaurios que se quieren asignar
        int numeroDinosaurios = 0;

        // Seleccionar dinosaurios compatibles
        List<Dinos> dinosauriosSeleccionados = dinosauriosCompatibles.stream()
                .limit(numeroDinosaurios)
                .toList();

        // Asignar los dinosaurios seleccionados a la instalación
        for (Dinos dinosaurio : dinosauriosSeleccionados) {
            guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
            System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación " + instalacion.getNombre());
        }
    }



    public void guardarRelacionDinosaurioInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionRepository.save(relacion);
        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    public List<Dinos> obtenerDinosauriosEnInstalacion(InstalacionE instalacion) {
        return dinosaurioInstalacionRepository.findByInstalacion(instalacion).stream()
                .map(DinosaurioInstalaciones::getDinosaurio)
                .collect(Collectors.toList());
    }

    public void eliminarRelacion(int idRelacion) {
        if (dinosaurioInstalacionRepository.existsById(idRelacion)) {
            dinosaurioInstalacionRepository.deleteById(idRelacion);
            System.out.println("Relación con ID " + idRelacion + " eliminada correctamente.");
        } else {
            throw new IllegalArgumentException("La relación con ID " + idRelacion + " no existe.");
        }
    }

    private boolean verificarCompatibilidadConInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        List<DinosaurioInstalaciones> relaciones = dinosaurioInstalacionRepository.findByInstalacion(instalacion);

        for (DinosaurioInstalaciones relacion : relaciones) {
            Dinos dinoExistente = relacion.getDinosaurio();

            if (!puedenCoexistir(dinosaurio, dinoExistente)) {
                System.out.println("Incompatibilidad detectada con dinosaurio existente: " + dinoExistente.getEspecie());
                return false;
            }
        }

        return true;
    }


}
