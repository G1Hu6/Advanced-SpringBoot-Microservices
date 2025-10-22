package com.ecom.inventory_service.dtos;

import lombok.*;

@Data
public class ProductDto {
    private Long id;

    private String name;

    private Double price;

    private Integer stock;
}
