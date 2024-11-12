package com.example.jurassicpark.AOP;

import com.example.jurassicpark.exceptiones.DinosaurioNotFoundException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    // Maneja la excepción DinosaurioNotFoundException
    @AfterThrowing(pointcut = "execution(* com.example.jurassicpark..*(..))", throwing = "ex")
    public void handleDinosaurioNotFound(DinosaurioNotFoundException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
