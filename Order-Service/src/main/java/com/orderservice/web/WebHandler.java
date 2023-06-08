package com.orderservice.web;

import com.orderservice.model.InventoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@AllArgsConstructor
public class WebHandler {

    private final WebClient.Builder webClient;

    public InventoryResponse[] getInventories(List<String> itemCodes) {
        return webClient.build().get()
                .uri("http://Inventory-Service/inventory",
                        uriBuilder -> uriBuilder.queryParam("itemCode", itemCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
    }

    public void subtractQuantity(String itemCode, Integer quantity) {
        webClient.build().put()
                .uri("http://Inventory-Service/inventory/{itemCode}/subtract/{quantity}", itemCode, quantity)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}










