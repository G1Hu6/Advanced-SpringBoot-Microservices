package com.ecom.inventory_service.services;

import com.ecom.inventory_service.dtos.OrderRequestDto;
import com.ecom.inventory_service.dtos.OrderRequestItemDto;
import com.ecom.inventory_service.dtos.ProductDto;
import com.ecom.inventory_service.entities.Product;
import com.ecom.inventory_service.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory() {
        log.info("Fetching all inventory items");
        List<Product> inventories = productRepository.findAll();
        return inventories.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id) {
        log.info("Fetching Product with ID: {}", id);
        Optional<Product> inventory = productRepository.findById(id);
        return inventory.map(item -> modelMapper.map(item, ProductDto.class))
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public Double reduceStock(OrderRequestDto orderRequestDto) {
        double totalPrice = 0.0;
        for(OrderRequestItemDto orderRequestItem : orderRequestDto.getItems()){
            Long productId = orderRequestItem.getProductId();
            Optional<Product> productOptional = productRepository.findById(productId);
            if(productOptional.isEmpty()){
                throw new RuntimeException("Product not exists with id : " + productId);
            }
            Product toSaveProduct = productOptional.get();
            Integer quantity = orderRequestItem.getQuantity();
            if(quantity > toSaveProduct.getStock()){
                throw new RuntimeException("Can not order Product at this time");
            }
            toSaveProduct.setStock(toSaveProduct.getStock()-quantity);
            productRepository.save(toSaveProduct);
            totalPrice += toSaveProduct.getPrice() * quantity;
        }
        return totalPrice;
    }
}
