package com.inventoryservice.service;

import com.inventoryservice.entity.Inventory;
import com.inventoryservice.mapper.InventoryMapper;
import com.inventoryservice.model.InventoryDTO;
import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public List<InventoryDTO> getInventories(List<String> itemCode) {
        return inventoryRepository.findByItemCodeIn(itemCode).stream()
                .map(inventoryMapper::toDTO)
                .toList();
    }


    public InventoryDTO getInventoryByItemCode(String itemCode) {
        return inventoryMapper.toDTO(inventoryRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with item code: " + itemCode)));
    }

    public InventoryDTO createInventory(InventoryDTO inventoryDTO) {
        return inventoryMapper.toDTO(inventoryRepository.save(inventoryMapper.toEntity(inventoryDTO)));
    }

    public InventoryDTO addQuantity(String itemCode, Integer quantity) {
        Inventory inventory = inventoryRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with item code: " + itemCode));
        inventory.setQuantity(inventory.getQuantity() + quantity);

        return inventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    public InventoryDTO subtractQuantity(String itemCode, Integer quantity) {
        Inventory inventory = inventoryRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with item code: " + itemCode));
        inventory.setQuantity(inventory.getQuantity() - quantity);

        return inventoryMapper.toDTO(inventoryRepository.save(inventory));
    }

    public InventoryDTO updateInventoryName(String itemCode, InventoryDTO inventoryDTO) {
        Inventory existingInventory = inventoryRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Oops... something went wrong"));

        existingInventory.setItemCode(inventoryDTO.getItemCode());

        return inventoryMapper.toDTO(inventoryRepository.save(existingInventory));
    }


    public void deleteInventory(String itemCode) {
       inventoryRepository.findByItemCode(itemCode)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with item code: " + itemCode));

        inventoryRepository.deleteByItemCode(itemCode);
    }
}
