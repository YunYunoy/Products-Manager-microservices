package com.orderservice.service;

import com.orderservice.entity.Order;
import com.orderservice.mapper.OrderLineItemMapper;
import com.orderservice.mapper.OrderMapper;
import com.orderservice.model.OrderDTO;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
        return orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }


    public void updateOrder(OrderDTO orderDTO) {
        orderMapper.toDTO(orderRepository.save(orderMapper.toEntity(orderDTO)));
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
