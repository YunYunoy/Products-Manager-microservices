package com.orderservice.web;

import com.orderservice.model.InventoryResponse;
import com.orderservice.model.ProductResponse;
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

    public void addQuantity(String itemCode, Integer quantity) {
        webClient.build().put()
                .uri("http://Inventory-Service/inventory/{itemCode}/add/{quantity}", itemCode, quantity)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public ProductResponse[] getProducts(List<String> names) {
        return webClient.build().get()
                .uri("http://Product-Service/products",
                        uriBuilder -> uriBuilder.queryParam("names", names).build())
                .retrieve()
                .bodyToMono(ProductResponse[].class)
                .block();
    }

}










