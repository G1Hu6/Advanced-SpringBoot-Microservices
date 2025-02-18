package com.aop.services.impl;

import com.aop.annotations.CustomAnnotation;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl {

    public PaymentServiceImpl(){
        log.info("Inside PaymentServiceImpl constructor");
    }

    public PaymentServiceImpl(String id){
        log.info("Inside PaymentServiceImpl parametrized constructor");
    }

    @Transactional
    public void processPayment(Long orderId){
        //business logic
        log.info("Processing payment select your payment method");
        System.out.println("Processing payment select your payment method");
    }

    @CustomAnnotation
    public void orderPackage(String productId){
        //business logic
        log.info("Processing order package inside PaymentService");
        System.out.println("Processing order package inside PaymentService");
    }
}
