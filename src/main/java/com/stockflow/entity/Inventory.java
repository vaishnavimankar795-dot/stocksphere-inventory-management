package com.stockflow.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "inventories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    
    @Column(nullable = false)
    private Integer quantity;
}
