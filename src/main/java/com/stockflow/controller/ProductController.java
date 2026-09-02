package com.stockflow.controller;

import com.stockflow.dto.ProductRequestDto;
import com.stockflow.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        try {
            Long productId = productService.createProduct(productRequestDto);
            
            Map<String, Object> response = Map.of(
                    "message", "Product created successfully",
                    "product_id", productId
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = Map.of(
                    "message", e.getMessage()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
