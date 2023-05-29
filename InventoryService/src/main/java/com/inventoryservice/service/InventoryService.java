package com.inventoryservice.service;

import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInInventory(String itemCode) {
        return inventoryRepository.findByItemCode(itemCode).isPresent();
    }
}
