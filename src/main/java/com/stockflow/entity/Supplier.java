package com.stockflow.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "supplier_products",
        joinColumns = @JoinColumn(name = "supplier_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
}
