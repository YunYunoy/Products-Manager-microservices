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
                .itemCode("Item A")
                .quantity(100)
                .build();

        Inventory inventory2 = Inventory.builder()
                .itemCode("Item B")
                .quantity(50)
                .build();

        Inventory inventory3 = Inventory.builder()
                .itemCode("Item C")
                .quantity(25)
                .build();

        Inventory inventory4 = Inventory.builder()
                .itemCode("Item D")
                .quantity(0)
                .build();

        inventoryRepository.saveAll(Arrays.asList(inventory1, inventory2, inventory3, inventory4));
        log.info("items in inventory: " + inventoryRepository.count());
    }
}
