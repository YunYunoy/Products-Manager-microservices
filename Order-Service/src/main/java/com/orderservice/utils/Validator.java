package com.orderservice.utils;

import com.orderservice.model.InventoryResponse;
import com.orderservice.model.OrderDTO;
import com.orderservice.model.OrderLineItemDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Validator {

    public boolean validateGetInventories(OrderDTO orderDTO,InventoryResponse[] inventoryResponses, List<String> itemCodes) {
        Map<String, Integer> requestedQuantities = orderDTO.getItemsList().stream()
                .collect(Collectors.toMap(OrderLineItemDTO::getItemCode, OrderLineItemDTO::getQuantity));

        Map<String, Integer> inventoryQuantities = Arrays.stream(inventoryResponses)
                .collect(Collectors.toMap(InventoryResponse::getItemCode, InventoryResponse::getQuantity));

        boolean isQuantityValid = itemCodes.stream()
                .allMatch(itemCode -> requestedQuantities.getOrDefault(itemCode, 0) <= inventoryQuantities.getOrDefault(itemCode, 0));

        boolean allItemsExistInInventory = new HashSet<>(Arrays.stream(inventoryResponses)
                .map(InventoryResponse::getItemCode)
                .collect(Collectors.toList()))
                .containsAll(itemCodes);

        if (!isQuantityValid) {
            throw new IllegalArgumentException("Requested quantity for one or more items exceeds available quantity");
        }

        if (!allItemsExistInInventory) {
            throw new IllegalArgumentException("One or more items do not exist in inventory");
        }
        return true;
    }



}
