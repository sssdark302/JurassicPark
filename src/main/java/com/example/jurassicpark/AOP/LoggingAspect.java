package com.example.jurassicpark.AOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log para cambios en el ciclo de vida
    @After("execution(* com.example.jurassicpark.ciclodevida.GestorCV.avanzarFase(..))")
    public void logPhaseChange() {
        logger.info("El ciclo de vida del dinosaurio ha avanzado de fase.");
    }

    // Log para creación de dinosaurios
    @After("execution(* com.example.jurassicpark.service.DinosaurioService.crearYAlmacenarDinosaurio(..))")
    public void logDinosaurioCreation() {
        logger.info("Se ha creado un nuevo dinosaurio.");
    }
    //log eliminar agregar un par mas
}