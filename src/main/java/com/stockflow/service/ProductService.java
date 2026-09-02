package com.stockflow.service;

import com.stockflow.dto.ProductRequestDto;
import com.stockflow.entity.Inventory;
import com.stockflow.entity.Product;
import com.stockflow.entity.Warehouse;
import com.stockflow.repository.InventoryRepository;
import com.stockflow.repository.ProductRepository;
import com.stockflow.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRepository inventoryRepository;
    
    @Transactional
    public Long createProduct(ProductRequestDto dto) {
        // Check if SKU already exists
        if (productRepository.existsBySku(dto.getSku())) {
            throw new IllegalArgumentException("Product with SKU '" + dto.getSku() + "' already exists");
        }
        
        // Validate warehouse exists
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(() -> new IllegalArgumentException("Warehouse with ID " + dto.getWarehouseId() + " not found"));
        
        // Create product
        Product product = Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .price(dto.getPrice())
                .threshold(10) // Default threshold
                .build();
        
        Product savedProduct = productRepository.save(product);
        
        // Create initial inventory
        Inventory inventory = Inventory.builder()
                .product(savedProduct)
                .warehouse(warehouse)
                .quantity(dto.getInitialQuantity())
                .build();
        
        inventoryRepository.save(inventory);
        
        return savedProduct.getId();
    }
}
