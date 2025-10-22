package com.ecom.order_service.clients;

import com.ecom.order_service.dtos.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service", path = "inventory")
public interface InventoryFeignClient {

    @PutMapping("/products/reduce-stock")
    Double reduceStock(@RequestBody OrderRequestDto orderRequestDto);
}
