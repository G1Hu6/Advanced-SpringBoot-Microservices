package com.aop.services.impl;

import com.aop.annotations.CustomAnnotation;
import com.aop.services.ShipmentService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    public ShipmentServiceImpl() {
        log.info("Inside ShipmentServiceImpl constructor");
    }

    //JoinPoint:- These are the specific points in your program where you might want
    //            to inject additional logic or behaviour.
    @Override
    @Transactional
    public String orderPackage(Long orderId) {
        // Secondary business logic...
        // log.info("Before orderPackage method");
        // Business logic
        try {
            log.info("Ordering your package...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Your order has been processed successfully!!! orderId: " + orderId;
    }

    // JoinPoint
    @Override
    @CustomAnnotation
    public String trackPackage(Long orderId) {
        // Secondary business logic...
        // log.info("Before trackPackage method");
        // Business logic
        try {
            log.info("Tracking your package...");
            Thread.sleep(500);
            throw new RuntimeException("Exception occurred during tacking package");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
