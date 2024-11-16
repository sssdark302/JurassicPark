package com.example.jurassicpark.service;

import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
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
    private DinosaurioDataStore DinosaurioDataStore;

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
        // Definir el número de dinosaurios que se quieren asignar
        int numeroDinosaurios = 2;

        // Obtener dinosaurios compatibles basados en el tipo de instalación
        List<Dinos> dinosauriosCompatibles = DinosaurioDataStore
                .getDinosauriosCompatibles(instalacion.getTipo());

        // Verificar que hay suficientes dinosaurios compatibles
        if (dinosauriosCompatibles.size() < numeroDinosaurios) {
            throw new IllegalArgumentException("No hay suficientes dinosaurios compatibles para la instalación " + instalacion.getNombre());
        }

        // Filtrar y limitar la lista de dinosaurios compatibles al número necesario
        List<Dinos> dinosauriosFiltrados = dinosauriosCompatibles.stream()
                .limit(numeroDinosaurios)
                .toList();

        // Guardar la relación entre cada dinosaurio filtrado y la instalación
        for (Dinos dinosaurio : dinosauriosFiltrados) {
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
