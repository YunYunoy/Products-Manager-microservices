package com.inventoryservice.controller;

import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    @GetMapping("/{itemCode}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInInventory(@PathVariable String itemCode) {
        return inventoryService.isInInventory(itemCode);
    }
}
