package com.example.jurassicpark.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    // Intercepta el método reproducir en DinosaurioService para validar permisos
    @Around("execution(* com.example.jurassicpark.ciclodevida.GestorCV.reproducirse(..))")
    public Object checkPermissions(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!tienePermisos()) {
            throw new SecurityException("No tienes permisos para reproducir dinosaurios.");
        }
        return joinPoint.proceed();
    }

    private boolean tienePermisos() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return auth.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }

}