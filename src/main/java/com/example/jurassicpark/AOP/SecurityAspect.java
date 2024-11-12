package com.example.jurassicpark.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    // Intercepta el método reproducir en DinosaurioService para validar permisos
    @Around("execution(* com.example.jurassicpark.CiclodeVida.GestorCV.reproducirse(..))")
    public Object checkPermissions(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!tienePermisos()) {
            System.out.println("Permiso denegado: El usuario no tiene permisos para reproducir dinosaurios.");
            return null;
        }
        // Procede con la ejecución si tiene permisos
        return joinPoint.proceed();
    }

    private boolean tienePermisos() {
        return true; 
    }
}
