package com.aop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.aop.services.impl.ShipmentServiceImpl.*(..))")
    public void beforeShipmentServiceMethods(JoinPoint joinPoint){
        log.info("Before method call : {}", joinPoint.getSignature());
    }
}
