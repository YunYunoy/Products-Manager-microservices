package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItem;
import com.orderservice.event.OrderCreatedEvent;
import com.orderservice.mapper.OrderLineItemMapper;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.model.InventoryResponse;
import com.orderservice.model.OrderDTO;
import com.orderservice.repository.OrderRepository;
import com.orderservice.utils.NotValidatedException;
import com.orderservice.utils.Validator;
import com.orderservice.web.WebHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    //TODO: implement receiving price for item from Product-Service
    public OrderDTO createOrder(OrderDTO orderDTO) {
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

        InventoryResponse[] inventoryResponses = webHandler.getInventories(itemCodes);

        boolean isValidated = validator.validateGetInventories(orderDTO, inventoryResponses, itemCodes);

        if (isValidated) {
            kafkaTemplate.send("orderNotification", new OrderCreatedEvent(order.getOrderNumber()));

            for (OrderLineItem item : lineItems) {
                webHandler.subtractQuantity(item.getItemCode(), item.getQuantity());
            }

            return orderMapper.toDTO(orderRepository.save(order));
        } else throw new NotValidatedException("Requested parameters are not valid");

    }


    public void updateOrder(OrderDTO orderDTO) {
        orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }


    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


}
