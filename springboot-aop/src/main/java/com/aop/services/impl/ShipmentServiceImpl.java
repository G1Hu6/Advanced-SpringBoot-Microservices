package com.aop.services.impl;

import com.aop.services.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    @Override
    public String orderPackage(Long orderId) {
        // Secondary business logic...
        log.info("Before orderPackage method");
        // Business logic
        try {
            log.info("Ordering your package...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Your order has been processed successfully!!! orderId: " + orderId;
    }

    @Override
    public String trackPackage(Long orderId) {
        // Secondary business logic...
        log.info("Before trackPackage method");
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
