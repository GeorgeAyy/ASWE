package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.example.demo.controllers.*.*(..))")
    public void controllerLayer() {
    }

    @Pointcut("execution(* com.example.demo.services.*.*(..))")
    public void serviceLayer() {
    }

    @Pointcut("execution(* com.example.demo.repositories.*.*(..))")
    public void repositoryLayer() {
    }

    @Before("controllerLayer()")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        logger.info("Before executing controller method: {}", joinPoint.getSignature());
    }

    @After("controllerLayer()")
    public void afterControllerMethod(JoinPoint joinPoint) {
        logger.info("After executing controller method: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "controllerLayer()", returning = "result")
    public void afterReturningControllerMethod(JoinPoint joinPoint, Object result) {
        logger.info("Controller method {} returned with value {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "controllerLayer()", throwing = "ex")
    public void afterThrowingControllerMethod(JoinPoint joinPoint, Exception ex) {
        logger.error("Controller method {} threw exception {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }

    @Before("serviceLayer()")
    public void beforeServiceMethod(JoinPoint joinPoint) {
        logger.info("Before executing service method: {}", joinPoint.getSignature());
    }

    @After("serviceLayer()")
    public void afterServiceMethod(JoinPoint joinPoint) {
        logger.info("After executing service method: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void afterReturningServiceMethod(JoinPoint joinPoint, Object result) {
        logger.info("Service method {} returned with value {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
    public void afterThrowingServiceMethod(JoinPoint joinPoint, Exception ex) {
        logger.error("Service method {} threw exception {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }

    @Before("repositoryLayer()")
    public void beforeRepositoryMethod(JoinPoint joinPoint) {
        logger.info("Before executing repository method: {}", joinPoint.getSignature());
    }

    @After("repositoryLayer()")
    public void afterRepositoryMethod(JoinPoint joinPoint) {
        logger.info("After executing repository method: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "repositoryLayer()", returning = "result")
    public void afterReturningRepositoryMethod(JoinPoint joinPoint, Object result) {
        logger.info("Repository method {} returned with value {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "repositoryLayer()", throwing = "ex")
    public void afterThrowingRepositoryMethod(JoinPoint joinPoint, Exception ex) {
        logger.error("Repository method {} threw exception {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }

    @Around("controllerLayer() || serviceLayer()")
    public Object timeTracker(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        logger.info("Execution time of {} is {} ms", joinPoint.getSignature(), timeTaken);
        return result;
    }
}
