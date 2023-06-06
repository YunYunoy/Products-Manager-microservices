package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItem;
import com.orderservice.event.OrderCreatedEvent;
import com.orderservice.mapper.OrderLineItemMapper;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.model.InventoryResponse;
import com.orderservice.model.OrderDTO;
import com.orderservice.model.OrderLineItemDTO;
import com.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineItemMapper orderLineItemMapper;
    private final WebClient.Builder webClient;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());

    }

    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return orderMapper.toDTO(order);
    }


    public OrderDTO createMultipleOrders(OrderDTO orderDTO) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .description(orderDTO.getDescription())
                .build();

        List<OrderLineItem> lineItems = orderDTO.getItemsList().stream()
                .map(orderLineItemMapper::toEntity)
                .collect(Collectors.toList());
        order.setItemsList(lineItems);

        List<String> itemCodes = order.getItemsList().stream()
                .map(OrderLineItem::getItemCode)
                .toList();

        InventoryResponse[] inventoryResponses = webClient.build().get()
                .uri("http://Inventory-Service/inventory",
                        uriBuilder -> uriBuilder.queryParam("itemCode", itemCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        Map<String, Integer> requestedQuantities = orderDTO.getItemsList().stream()
                .collect(Collectors.toMap(OrderLineItemDTO::getItemCode, OrderLineItemDTO::getQuantity));

        Map<String, Integer> inventoryQuantities = Arrays.stream(inventoryResponses)
                .collect(Collectors.toMap(InventoryResponse::getItemCode, InventoryResponse::getQuantity));

        for (String itemCode : itemCodes) {
            int requestedQuantity = requestedQuantities.getOrDefault(itemCode, 0);
            int inventoryQuantity = inventoryQuantities.getOrDefault(itemCode, 0);

            if (requestedQuantity > inventoryQuantity) {
                throw new IllegalArgumentException("Requested quantity for item " + itemCode + " exceeds available quantity");
            }
        }

        boolean allItemsExistInInventory = new HashSet<>(Arrays.stream(inventoryResponses)
                .map(InventoryResponse::getItemCode)
                .collect(Collectors.toList()))
                .containsAll(itemCodes);

        if (allItemsExistInInventory) {
            kafkaTemplate.send("orderNotification", new OrderCreatedEvent(order.getOrderNumber()));
            return orderMapper.toDTO(orderRepository.save(order));
        } else {
            throw new IllegalArgumentException("One or more items do not exist in inventory");
        }
    }


    public void updateOrder(OrderDTO orderDTO) {
        orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }


    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


}
