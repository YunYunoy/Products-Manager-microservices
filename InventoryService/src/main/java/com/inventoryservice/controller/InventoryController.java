package com.inventoryservice.controller;

import com.inventoryservice.model.InventoryDTO;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;


    // http://Inventory_Service/inventory?itemCode=Item_A&itemCode=Item_B
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getInventories(@RequestParam List<String> itemCode) {
        return ResponseEntity.ok(inventoryService.getInventories(itemCode));
    }


    @GetMapping("/{itemCode}")
    public ResponseEntity<InventoryDTO> getInventoryByItemCode(@PathVariable String itemCode) {
        InventoryDTO inventoryDTO = inventoryService.getInventoryByItemCode(itemCode);
        return ResponseEntity.ok(inventoryDTO);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@Validated @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO createdInventory = inventoryService.createInventory(inventoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInventory);
    }

    @PutMapping("/{itemCode}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable String itemCode, @Validated @RequestBody InventoryDTO inventoryDTO) {
        InventoryDTO updatedInventory = inventoryService.updateInventoryName(itemCode, inventoryDTO);
        return ResponseEntity.ok(updatedInventory);
    }


    @PutMapping("/{itemCode}/add/{quantity}")
    public ResponseEntity<InventoryDTO> addQuantity(@PathVariable String itemCode, @PathVariable Integer quantity) {
        InventoryDTO updatedInventory = inventoryService.addQuantity(itemCode, quantity);
        return ResponseEntity.ok(updatedInventory);
    }

    @PutMapping("/{itemCode}/subtract/{quantity}")
    @ResponseStatus(HttpStatus.OK)
    public void subtractQuantity(@PathVariable String itemCode, @PathVariable Integer quantity) {
        inventoryService.subtractQuantity(itemCode, quantity);
    }

    @DeleteMapping("/{itemCode}")
    public ResponseEntity<Void> deleteInventory(@PathVariable String itemCode) {
        inventoryService.deleteInventory(itemCode);
        return ResponseEntity.noContent().build();
    }
}
