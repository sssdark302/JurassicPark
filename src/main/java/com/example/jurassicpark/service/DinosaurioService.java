package com.example.jurassicpark.service;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.datastores.DinosaurioDataStore;
import com.example.jurassicpark.models.entidades.InstalacionE;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.repository.DinosaurioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosaurioService {

    @Autowired
    @Lazy
    private DinosaurioRepository dinosaurioRepository;

    @Autowired
    @Lazy
    private DinosaurioFactory dinosaurioFactory;


    public Dinos crearDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo,
                                 Sexo sexo, double hpMaxima, boolean tuvoHijos, FaseCicloDeVida fase, String habitat, String dieta) {
        Dinos dinosaurio = dinosaurioFactory.crearDinosaurio(
                tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos, fase, habitat, dieta
        );

        return dinosaurioRepository.save(dinosaurio);
    }

    public List<Dinos> listarDinosaurios() {
        return dinosaurioRepository.findAll();
    }

    public void eliminarDinosaurioPorId(int id) {
        if (dinosaurioRepository.existsById(id)) {
            dinosaurioRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Dinosaurio con ID " + id + " no encontrado.");
        }
    }

    public List<Dinos> buscarDinosauriosPorFaseYEspecie(FaseCicloDeVida faseCicloDeVida, String especie) {
        return dinosaurioRepository.findByFaseCicloDeVidaAndEspecie(faseCicloDeVida, especie);
    }

    public void eliminarDinosaurio(Dinos dinosaurio) {
        dinosaurioRepository.delete(dinosaurio);
    }
}
