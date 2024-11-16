package com.example.jurassicpark.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotifAspect {

    // Pointcut para interceptar métodos relacionados con dinosaurios
    @Pointcut("execution(* com.example.jurassicpark.controllers.DinosaurioController.*(..))")
    public void dinosaurioActions() {}

    // Pointcut para interceptar métodos relacionados con instalaciones
    @Pointcut("execution(* com.example.jurassicpark.controllers.InstalacionController.*(..))")
    public void instalacionActions() {}

    // Notificación después de una acción en DinosaurioController
    @After("dinosaurioActions()")
    public void notifyDinosaurioChange() {
        // Lógica de notificación (log o servicio externo)
        System.out.println("[Notificación]: Una acción se realizó en DinosaurioController.");
    }

    // Notificación después de una acción en InstalacionController
    @After("instalacionActions()")
    public void notifyInstalacionChange() {
        // Lógica de notificación (log o servicio externo)
        System.out.println("[Notificación]: Una acción se realizó en InstalacionController.");
    }
}