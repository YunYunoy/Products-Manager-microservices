package com.inventoryservice.service;

import com.inventoryservice.model.InventoryResponse;
import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInInventory(List<String> itemCode) {
        return inventoryRepository.findByItemCodeIn(itemCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .itemCode(inventory.getItemCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()

                ).toList();
    }

//TODO: create CRUD methods


}
