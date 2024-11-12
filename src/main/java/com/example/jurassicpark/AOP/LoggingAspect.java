package com.example.jurassicpark.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Intercepta el método avanzarFase en GestorCV para registrar el cambio de fase
    @After("execution(* com.example.jurassicpark.ciclodevida.GestorCV.avanzarFase(..))")
    public void logAfterPhaseChange() {
        System.out.println("El ciclo de vida del dinosaurio ha avanzado de fase.");
    }
}
