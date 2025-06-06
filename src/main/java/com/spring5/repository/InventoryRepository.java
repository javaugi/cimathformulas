package com.spring5.repository;

import com.spring5.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Inventory findByProductId(@Param("productId") Long productId);
}
