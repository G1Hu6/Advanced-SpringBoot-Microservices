package com.aop.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl {

    public void processPayment(Long orderId){
        //business logic
        log.info("Processing payment select your payment method");
        System.out.println("Processing payment select your payment method");
    }

    public void orderPackage(String productId){
        //business logic
        log.info("Processing order package inside PaymentService");
        System.out.println("Processing order package inside PaymentService");
    }
}
