package com.inventoryservice.bootstrap;

import com.inventoryservice.entity.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoad implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {

        Inventory inventory1 = Inventory.builder()
                .itemCode("iPhone_X")
                .quantity(100)
                .build();

        Inventory inventory2 = Inventory.builder()
                .itemCode("Samsung_Galaxy_S21")
                .quantity(50)
                .build();

        Inventory inventory3 = Inventory.builder()
                .itemCode("Sony_WH_1000XM4")
                .quantity(25)
                .build();

        Inventory inventory4 = Inventory.builder()
                .itemCode("Nintendo_Switch")
                .quantity(0)
                .build();

        inventoryRepository.saveAll(Arrays.asList(inventory1, inventory2, inventory3, inventory4));
        log.info("items in inventory: " + inventoryRepository.count());
    }
}
