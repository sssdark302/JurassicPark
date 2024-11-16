package com.example.jurassicpark.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.jurassicpark.service.InstalacionService.*(..))")
    public void instalacionServiceMethods() {}

    @Pointcut("execution(* com.example.jurassicpark.service.DinosaurioService.*(..))")
    public void dinosaurioServiceMethods() {}

    @Before("instalacionServiceMethods() || dinosaurioServiceMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Ejecutando método: {}", joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            logger.info("Con argumentos: ");
            for (Object arg : args) {
                logger.info("  - {}", arg);
            }
        }
    }

    @AfterReturning(value = "instalacionServiceMethods() || dinosaurioServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Método finalizado: {}", joinPoint.getSignature().toShortString());
        if (result != null) {
            logger.info("Resultado: {}", result);
        }
    }

    @AfterThrowing(value = "instalacionServiceMethods() || dinosaurioServiceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Excepción en método: {}", joinPoint.getSignature().toShortString());
        logger.error("Detalle de la excepción: {}", exception.getMessage());
    }
}
