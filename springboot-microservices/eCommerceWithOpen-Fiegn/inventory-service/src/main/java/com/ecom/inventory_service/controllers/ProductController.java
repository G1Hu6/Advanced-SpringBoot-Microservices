package com.ecom.inventory_service.controllers;


import com.ecom.inventory_service.clients.OrderFeignClient;
import com.ecom.inventory_service.dtos.OrderRequestDto;
import com.ecom.inventory_service.dtos.ProductDto;
import com.ecom.inventory_service.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    private final OrderFeignClient orderFeignClient ;

    // Call order-service from inventory-service using discovery server(eureka server)
    @GetMapping("fetchOrders")
    public String fetchFromOrderService(HttpServletRequest httpServletRequest){
        // fetching request header in controller
        log.info(httpServletRequest.getHeader("x-my-header"));
        // Using open feign client
        return orderFeignClient.helloOrdersKing();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllInventory() {
        List<ProductDto> inventories = productService.getAllInventory();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id) {
        ProductDto inventory = productService.getProductById(id);
        return ResponseEntity.ok(inventory);
    }

    @PutMapping("reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto orderRequestDto){
        Double totalPrice = productService.reduceStock(orderRequestDto);
        return ResponseEntity.ok(totalPrice);
    }
}
