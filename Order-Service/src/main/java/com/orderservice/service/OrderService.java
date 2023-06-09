package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItem;
import com.orderservice.event.OrderCreatedEvent;
import com.orderservice.mapper.OrderLineItemMapper;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.model.InventoryResponse;
import com.orderservice.model.OrderDTO;
import com.orderservice.model.ProductResponse;
import com.orderservice.repository.OrderRepository;
import com.orderservice.utils.Validator;
import com.orderservice.web.WebHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineItemMapper orderLineItemMapper;
    private final WebHandler webHandler;
    private final Validator validator;
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

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .description(orderDTO.getDescription())
                .build();

        List<OrderLineItem> lineItems = orderDTO.getItemsList().stream()
                .map(orderLineItemMapper::toEntity)
                .collect(Collectors.toList());

        order.setItemsList(lineItems);

        List<String> itemCodes = lineItems.stream()
                .map(OrderLineItem::getItemCode)
                .toList();

        InventoryResponse[] inventoryResponses = webHandler.getInventories(itemCodes);

        validator.validateGetInventories(orderDTO, inventoryResponses, itemCodes);

        ProductResponse[] productResponses = webHandler.getProducts(itemCodes);
        setPricesForItems(lineItems, productResponses);
        lineItems.forEach(item -> webHandler.subtractQuantity(item.getItemCode(), item.getQuantity()));

        kafkaTemplate.send("orderNotification", new OrderCreatedEvent(order.getOrderNumber()));
        return orderMapper.toDTO(orderRepository.save(order));
    }

    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        List<OrderLineItem> lineItems = order.getItemsList();
        lineItems.forEach(item -> webHandler.addQuantity(item.getItemCode(), item.getQuantity()));

        orderRepository.deleteById(orderId);
    }

    public void updateOrder(Long id, OrderDTO orderDTO) {
        orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        deleteOrder(id);
        createOrder(orderDTO);
    }


    private void setPricesForItems(List<OrderLineItem> lineItems, ProductResponse[] productResponses) {
        Map<String, BigDecimal> priceMap = Arrays.stream(productResponses)
                .collect(Collectors.toMap(ProductResponse::getName, ProductResponse::getPrice));

        lineItems.forEach(item -> item.setPrice(priceMap.get(item.getItemCode())));
    }


}
