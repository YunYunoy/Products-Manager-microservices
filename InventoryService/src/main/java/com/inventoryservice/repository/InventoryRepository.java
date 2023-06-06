package com.inventoryservice.repository;

import com.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByItemCodeIn(List<String> itemCode);

    Optional<Inventory> findByItemCode(String itemCode);

    void deleteByItemCode(String itemCode);

    boolean existsByItemCode(String itemCode);
}
