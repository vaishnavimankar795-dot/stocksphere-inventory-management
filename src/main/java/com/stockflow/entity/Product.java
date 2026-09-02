package com.stockflow.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String sku;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
    
    @Column(name = "threshold_quantity")
    private Integer threshold;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "product_bundles",
        joinColumns = @JoinColumn(name = "parent_product_id"),
        inverseJoinColumns = @JoinColumn(name = "bundled_product_id")
    )
    private Set<Product> bundledProducts;
    
    @ManyToMany(mappedBy = "bundledProducts")
    private Set<Product> parentProducts;
    
    @ManyToMany(mappedBy = "products")
    private List<Supplier> suppliers;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Inventory> inventories;
}
