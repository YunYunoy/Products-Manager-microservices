package com.productservice.web;

import com.productservice.model.InventoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class WebHandler {

    private final WebClient.Builder webClient;

    public void createInventory(InventoryDTO inventoryDTO) {
        webClient.build().post()
                .uri("http://Inventory-Service/inventory")
                .body(BodyInserters.fromValue(inventoryDTO))
                .retrieve()
                .toEntity(InventoryDTO.class)
                .block();
    }

    public Mono<InventoryDTO> updateInventory(String itemCode, InventoryDTO inventoryDTO) {
        return webClient.build().put()
                .uri("http://Inventory-Service/inventory/{itemCode}", itemCode)
                .body(BodyInserters.fromValue(inventoryDTO))
                .retrieve()
                .bodyToMono(InventoryDTO.class);
    }

    public Mono<Void> deleteInventory(String itemCode) {
        return webClient.build().delete()
                .uri("http://Inventory-Service/inventory/{itemCode}", itemCode)
                .retrieve()
                .bodyToMono(Void.class);
    }

}
