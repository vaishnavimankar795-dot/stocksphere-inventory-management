package com.stockflow.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LowStockAlertDto {
    private Long productId;
    private String productName;
    private String sku;
    private Long warehouseId;
    private String warehouseName;
    private Integer currentStock;
    private Integer threshold;
    private Integer daysUntilStockout;
    private SupplierDto supplier;
}
