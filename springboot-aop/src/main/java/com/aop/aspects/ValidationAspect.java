package com.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.processing.ProcessingEnvironment;

//@Aspect
@Slf4j
@Component
public class ValidationAspect {

    @Around("allServiceClassMethods()")
    public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Inside validate id method");
        Object[] args = proceedingJoinPoint.getArgs();
        Long orderId = (Long)args[0];
        if(orderId > 0) return  proceedingJoinPoint.proceed();
        return "Can not call with -ve orderId";
    }

    @Pointcut("execution(* com.aop.services.impl.*.*(..))")
    public void allServiceClassMethods(){

    }
}
