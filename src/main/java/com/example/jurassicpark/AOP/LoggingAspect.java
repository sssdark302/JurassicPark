package com.example.jurassicpark.AOP;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @After("execution(* com.example.jurassicpark.ciclodevida.GestorCV.avanzarFase(..))")
    public void logAfterPhaseChange() {
        System.out.println("El ciclo de vida del dinosaurio ha avanzado de fase.");
    }
}
