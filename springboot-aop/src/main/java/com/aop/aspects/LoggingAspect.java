package com.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/*
    Aspect:- An aspect is a module that encapsulate a single cross-cutting concern.
             e.g. LoggingAspect class is called as Aspect here.
    JoinPoint:- These are the specific points in your program where you might want
                to inject additional logic or behaviour.
                e.g. [before each method's in ShipmentServiceImpl]
    Advice:- It's the code assigned with the aspect that gets executed when a
             specific JoinPoint is reached.
             e.g. complete beforeShipmentServiceMethods() is called as Advice.
    PointCut:- They are the expression that match with certain join points.
               e.g. execution(*com.aop.services.impl.ShipmentServiceImpl.*(..))
               this expression is called as PointCut.
    Weaving:- This is the process by which aspects are injected into the main
              business logic.It can happen at different times: compile-time,
              run-time, load-time.
*/
//@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // 1.execution kind pointcut

//    @Before("execution(* com.aop.services.impl.ShipmentServiceImpl.orderPackage(..))")
//    public void beforeShipmentServiceMethods(JoinPoint joinPoint){
//        log.info("Before specific orderPackage method call : {}", joinPoint.getSignature());
//    }
//
//    @Before("execution(* orderPackage(..))")
//    public void beforeOrderPackage(JoinPoint joinPoint){
//        log.info("Before orderPackage in all call : {}", joinPoint.getSignature());
//    }

//    @Before("serviceMethodsPointcut()")
//    public void beforeAllServicesClassMethods(JoinPoint joinPoint){
//        log.info("Before all service method call : {}", joinPoint.getSignature());
//    }
//
//    @After("serviceMethodsPointcut()")
//    public void afterAllServicesClassMethods(JoinPoint joinPoint){
//        log.info("After all service method call : {}", joinPoint.getSignature());
//    }

    // 2.within kind pointcut
//    @Before("within(com.aop..*)")
//    public void withInServicePackage(JoinPoint joinPoint){
//        log.info("Before all methods, fields and constructor inside service : {}", joinPoint.getKind());
//    }

    // 3.annotation kind pointcut
//    @After("@annotation(jakarta.transaction.Transactional)")
//    public void afterAnyTransactionalMethod(JoinPoint joinPoint){
//        log.info("After transactional method : {}", joinPoint.getSignature());
//    }
//
//    @After("@annotation(com.aop.annotations.CustomAnnotation)")
//    public void afterAnyCustomAnnotationMethod(JoinPoint joinPoint){
//        log.info("After custom annotation method : {}", joinPoint.getSignature());
//    }

    @After("pointcutWithLogicalOperators()")
    public void afterCustomPointcut(JoinPoint joinPoint){
        log.info("After logical combination method : {}", joinPoint.getSignature());
    }

    // Declaring Pointcut
    @Pointcut("execution(* com.aop.services.impl.*.*(..))")
    public void serviceMethodsPointcut(){

    }

    @Pointcut("@annotation(com.aop.annotations.CustomAnnotation) && within(com.aop.services.impl.PaymentServiceImpl)")
    public void pointcutWithLogicalOperators(){

    }

}
