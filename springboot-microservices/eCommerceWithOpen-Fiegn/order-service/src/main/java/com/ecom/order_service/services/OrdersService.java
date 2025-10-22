package com.ecom.order_service.services;

import com.ecom.order_service.clients.InventoryFeignClient;
import com.ecom.order_service.dtos.OrderRequestDto;
import com.ecom.order_service.entities.Order;
import com.ecom.order_service.entities.OrderItem;
import com.ecom.order_service.entities.OrderStatus;
import com.ecom.order_service.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {

    private final OrdersRepository orderRepository;
    private final ModelMapper modelMapper;
    private final InventoryFeignClient inventoryFeignClient;

    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderRequestDto.class)).toList();
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching order with ID: {}", id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return modelMapper.map(order, OrderRequestDto.class);
    }

    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        Double totalPrice = inventoryFeignClient.reduceStock(orderRequestDto);
        Order order = modelMapper.map(orderRequestDto, Order.class);
        for(OrderItem orderItem : order.getItems()){
            orderItem.setOrder(order);
        }
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderRequestDto.class);
    }
}










