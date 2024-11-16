package com.example.jurassicpark.service;

import com.example.jurassicpark.ciclodevida.FaseCicloDeVida;
import com.example.jurassicpark.models.Dinosaurio;
import com.example.jurassicpark.models.factorias.DinosaurioFactory;
import com.example.jurassicpark.models.Sexo;
import com.example.jurassicpark.models.entidades.Dinos;
import com.example.jurassicpark.repository.DinosaurioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DinosaurioService {

    @Autowired
    private DinosaurioRepository dinosaurioRepository;

    @Autowired
    private DinosaurioFactory dinosaurioFactory;

    @Autowired
    private InstalacionService instalacionService;

    public List<Dinos> listarDinosaurios() {
        return dinosaurioRepository.findAll();
    }

    public void crearYAlmacenarDinosaurio(String tipo, String especie, int edad, double alturaMaxima, int pesoMaximo, Sexo sexo, double hpMaxima, boolean tuvoHijos, FaseCicloDeVida faseCicloDeVida, String habitat) {
        Dinos dinosaurio = dinosaurioFactory.crearDinosaurio(tipo, especie, edad, alturaMaxima, pesoMaximo, sexo, hpMaxima, tuvoHijos, faseCicloDeVida, habitat);
        dinosaurioRepository.save(dinosaurio);
        enviarDinosaurioAInstalacion(dinosaurio);
    }

    private void enviarDinosaurioAInstalacion(Dinos dinosaurio) {
        instalacionService.asignarDinosaurioAInstalacion(dinosaurio);
    }
    public Dinos buscarDinosaurioPorId(int id) {
        return (Dinos) dinosaurioRepository.findDinosaurioById(id);
    }

    public void eliminarDinosaurio(Dinos dinosaurio) {
        dinosaurioRepository.delete(dinosaurio);
    }

    public void eliminarDinosaurioPorId(int id) {
        dinosaurioRepository.deleteById(id);
    }

    public List<Dinos> buscarDinosauriosPorFaseYEspecie(FaseCicloDeVida fase, String especie) {
        return dinosaurioRepository.findByFaseCicloDeVidaAndEspecie(fase, especie);
    }

}
