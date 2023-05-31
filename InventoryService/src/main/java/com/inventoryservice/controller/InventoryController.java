package com.inventoryservice.controller;

import com.inventoryservice.model.InventoryResponse;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    // http://localhost:8082/inventory?itemCode=Item_A&itemCode=Item_B
    // http://Inventory_Service/inventory?itemCode=Item_A

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInInventory(@RequestParam List<String> itemCode) {
        return inventoryService.isInInventory(itemCode);
    }
}
