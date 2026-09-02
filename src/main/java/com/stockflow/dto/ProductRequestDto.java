package com.stockflow.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    @NotBlank(message = "Product name is required")
    @Size(max = 255, message = "Product name must not exceed 255 characters")
    private String name;
    
    @NotBlank(message = "SKU is required")
    @Size(max = 100, message = "SKU must not exceed 100 characters")
    private String sku;
    
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have maximum 8 integer digits and 2 decimal places")
    private BigDecimal price;
    
    @NotNull(message = "Warehouse ID is required")
    @Positive(message = "Warehouse ID must be positive")
    private Long warehouseId;
    
    @NotNull(message = "Initial quantity is required")
    @Min(value = 0, message = "Initial quantity must be non-negative")
    private Integer initialQuantity;
}
