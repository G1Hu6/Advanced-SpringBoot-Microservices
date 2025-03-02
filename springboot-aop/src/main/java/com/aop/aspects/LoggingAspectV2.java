package com.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspectV2 {

//    @After("allServiceClassMethods()")
//    public void afterReturningMethod(JoinPoint joinPoint){
//        log.info("After a method call {}",joinPoint.getSignature());
//    }

    @AfterReturning(value = "allServiceClassMethods()", returning = "retValue")
    public void afterReturningMethod(JoinPoint joinPoint, Object retValue){
        log.info("After returning method call {}",joinPoint.getSignature());
        log.info("Return value = {}",retValue);
    }

    @AfterThrowing("allServiceClassMethods()")
    public void afterThrowingMethod(JoinPoint joinPoint){
        log.info("After throwing error method call {}",joinPoint.getSignature());
    }

    @Around("allServiceClassMethods()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object renValue = proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();
        Long diff = endTime - startTime;
        log.info("Time taken for {} is : {}",proceedingJoinPoint.getSignature(),diff);
        return renValue;
    }

    @Pointcut("execution(* com.aop.services.impl.*.*(..))")
    public void allServiceClassMethods(){

    }
}
