package com.stockflow.repository;

import com.stockflow.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    
    @Query("SELECT i FROM Inventory i " +
           "JOIN FETCH i.product p " +
           "JOIN FETCH i.warehouse w " +
           "JOIN FETCH w.company c " +
           "LEFT JOIN FETCH p.suppliers s " +
           "WHERE c.id = :companyId " +
           "AND i.quantity < p.threshold " +
           "AND p.threshold IS NOT NULL")
    List<Inventory> findLowStockInventoriesByCompanyId(@Param("companyId") Long companyId);
}
