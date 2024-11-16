package com.example.jurassicpark.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);

    @Around("execution(* com.example.jurassicpark.ciclodevida.GestorCV.reproducirse(..))")
    public Object secureReproduction(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            logger.warn("Acceso denegado. Usuario no autenticado intentó realizar una acción.");
            throw new SecurityException("Acceso denegado. Usuario no autenticado.");
        }

        // Comprobación de roles (ejemplo)
        if (!auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            logger.warn("Acceso denegado. Usuario sin permisos intentó realizar una acción.");
            throw new SecurityException("Acceso denegado. Permisos insuficientes.");
        }

        logger.info("Acción permitida para el usuario: {}", auth.getName());
        return joinPoint.proceed();
    }
}