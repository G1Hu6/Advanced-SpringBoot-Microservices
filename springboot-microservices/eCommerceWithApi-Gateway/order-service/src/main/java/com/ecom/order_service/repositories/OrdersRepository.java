package com.ecom.order_service.repositories;

import com.ecom.order_service.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
