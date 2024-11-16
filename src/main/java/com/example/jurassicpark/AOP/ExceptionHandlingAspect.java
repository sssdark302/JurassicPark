package com.example.jurassicpark.AOP;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    // Manejo de excepciones de dinosaurios
    @AfterThrowing(pointcut = "execution(* com.example.jurassicpark.controllers.DinosaurioController.*(..))", throwing = "ex")
    public void handleDinosaurioException(Exception ex) {
        logger.error("[Error]: Excepción en DinosaurioController: {}", ex.getMessage(), ex);
    }

    // Manejo de excepciones de instalaciones
    @AfterThrowing(pointcut = "execution(* com.example.jurassicpark.controllers.InstalacionController.*(..))", throwing = "ex")
    public void handleInstalacionException(Exception ex) {
        logger.error("[Error]: Excepción en InstalacionController: {}", ex.getMessage(), ex);
    }

    // Manejo genérico para excepciones no manejadas
    @AfterThrowing(pointcut = "execution(* com.example.jurassicpark..*(..))", throwing = "ex")
    public void handleGenericException(Exception ex) {
        logger.error("[Error]: Excepción genérica detectada: {}", ex.getMessage(), ex);}
}