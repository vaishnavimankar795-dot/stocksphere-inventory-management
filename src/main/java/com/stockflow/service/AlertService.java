package com.stockflow.service;

import com.stockflow.dto.LowStockAlertDto;
import com.stockflow.dto.SupplierDto;
import com.stockflow.entity.Inventory;
import com.stockflow.entity.Product;
import com.stockflow.entity.Supplier;
import com.stockflow.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {
    
    private final InventoryRepository inventoryRepository;
    
    public List<LowStockAlertDto> getLowStockAlerts(Long companyId) {
        List<Inventory> lowStockInventories = inventoryRepository.findLowStockInventoriesByCompanyId(companyId);
        
        return lowStockInventories.stream()
                .map(this::mapToLowStockAlertDto)
                .collect(Collectors.toList());
    }
    
    private LowStockAlertDto mapToLowStockAlertDto(Inventory inventory) {
        Product product = inventory.getProduct();
        
        // Calculate days until stockout (mocked as per requirement)
        int avgDailySales = 1; // Mocked value as specified
        int daysUntilStockout = inventory.getQuantity() / avgDailySales;
        
        // Get first supplier if available
        SupplierDto supplierDto = null;
        if (product.getSuppliers() != null && !product.getSuppliers().isEmpty()) {
            Supplier firstSupplier = product.getSuppliers().get(0);
            supplierDto = new SupplierDto(
                    firstSupplier.getId(),
                    firstSupplier.getName(),
                    firstSupplier.getContactEmail()
            );
        }
        
        return LowStockAlertDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .sku(product.getSku())
                .warehouseId(inventory.getWarehouse().getId())
                .warehouseName(inventory.getWarehouse().getName())
                .currentStock(inventory.getQuantity())
                .threshold(product.getThreshold())
                .daysUntilStockout(daysUntilStockout)
                .supplier(supplierDto)
                .build();
    }
}
