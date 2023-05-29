package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItem;
import com.orderservice.mapper.OrderLineItemMapper;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.model.OrderDTO;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineItemMapper orderLineItemMapper;

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
        // Map OrderDTO to Order entity using the builder pattern
        Order order = Order.builder()
                .orderNumber(orderDTO.getOrderNumber())
                .description(orderDTO.getDescription())
                .build();

        // Map OrderLineItemDTOs to OrderLineItem entities
        List<OrderLineItem> lineItems = orderDTO.getItemsList().stream()
                .map(orderLineItemMapper::toEntity)
                .collect(Collectors.toList());

        // Set the OrderLineItems for the Order
        order.setItemsList(lineItems);

        // Save the Order entity
        Order savedOrder = orderRepository.save(order);

        // Map the saved Order entity back to OrderDTO
        return orderMapper.toDTO(savedOrder);
    }


    public void updateOrder(OrderDTO orderDTO) {
        orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
