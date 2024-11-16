package com.example.jurassicpark.service;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.factorias.InstalacionFactory;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.models.entidades.DinosaurioInstalaciones;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.repository.DinosaurioInstalacionRepository;
import com.example.jurassicpark.repository.InstalacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstalacionService {

    @Autowired
    private InstalacionRepository instalacionRepository;

    @Autowired
    private InstalacionFactory instalacionFactory;

    @Autowired
    private DinosaurioInstalacionRepository dinosaurioInstalacionesRepository;

    private void guardarRelacionDinosaurioInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        DinosaurioInstalaciones relacion = new DinosaurioInstalaciones(dinosaurio, instalacion);
        dinosaurioInstalacionesRepository.save(relacion);
        System.out.println("Dinosaurio " + dinosaurio.getEspecie() + " asignado a la instalación: " + instalacion.getNombre());
    }

    private boolean verificarCompatibilidadConInstalacion(Dinos dinosaurio, InstalacionE instalacion) {
        List<DinosaurioInstalaciones> relaciones = dinosaurioInstalacionesRepository.findByInstalacion(instalacion);

        for (DinosaurioInstalaciones relacion : relaciones) {
            Dinos dinoExistente = relacion.getDinosaurio();

            // Reglas de coexistencia
            if (!puedenCoexistir(dinosaurio, dinoExistente)) {
                System.out.println("Incompatibilidad detectada con dinosaurio existente: " + dinoExistente.getEspecie());
                return false;
            }
        }

        return true;
    }
    private boolean puedenCoexistir(Dinos dino1, Dinos dino2) {
        // Ejemplo básico: carnivoros y herbívoros no pueden coexistir
        if (dino1.getTipo().equals("Carnivoro") && dino2.getTipo().equals("Herbivoro")) {
            return false;
        }
        if (dino1.getTipo().equals("Herbivoro") && dino2.getTipo().equals("Carnivoro")) {
            return false;
        }
        return true;
    }

    public void crearYAlmacenarInstalacion(String nombre, int capacidad, String tipo, double terreno, String seguridad, String descripcion, int personal, String horario, String habitat, String dieta) {
        InstalacionE instalacionE = instalacionFactory.crearInstalacion(nombre, capacidad, tipo, terreno, seguridad, descripcion, personal, horario, habitat, dieta);
        instalacionRepository.save(instalacionE);
    }
    public void asignarDinosaurioAInstalacion(Dinos dinosaurio) {
        String habitat = dinosaurio.getHabitat();
        String dieta = dinosaurio.getTipo();

        switch (habitat) {
            case "Acuatico":
                asignarDinosaurioAcuatico(dinosaurio, dieta);
                break;

            case "Terrestre":
                asignarDinosaurioTerrestre(dinosaurio, dieta);
                break;

            case "Aereo":
                asignarDinosaurioAereo(dinosaurio, dieta);
                break;

            default:
                throw new IllegalArgumentException("Hábitat desconocido: " + habitat);
        }
    }

    private void asignarDinosaurioAcuatico(Dinos dinosaurio, String dieta) {
        InstalacionE instalacion = instalacionRepository.findByHabitatAndTipoDieta("Acuatico", dieta);

        if (instalacion == null) {
            throw new IllegalArgumentException("No se encontró una instalación acuática para dieta " + dieta);
        }

        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }

    private void asignarDinosaurioTerrestre(Dinos dinosaurio, String dieta) {
        InstalacionE instalacion = instalacionRepository.findByHabitatAndTipoDieta("Terrestre", dieta);

        if (instalacion == null) {
            throw new IllegalArgumentException("No se encontró una instalación terrestre para dieta " + dieta);
        }

        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }

    private void asignarDinosaurioAereo(Dinos dinosaurio, String dieta) {
        InstalacionE instalacion = instalacionRepository.findByHabitatAndTipoDieta("Aereo", dieta);

        if (instalacion == null) {
            throw new IllegalArgumentException("No se encontró una instalación aerea para dieta " + dieta);
        }

        if (!verificarCompatibilidadConInstalacion(dinosaurio, instalacion)) {
            throw new IllegalArgumentException("La instalación ya contiene dinosaurios incompatibles");
        }

        guardarRelacionDinosaurioInstalacion(dinosaurio, instalacion);
    }
    private void eliminarInstalacion(String tipo) { //cambiarrlo despues
        instalacionRepository.deleteInstalacionByTipo(tipo);
    }
}
