package com.orderservice.controller;

import com.orderservice.model.OrderDTO;
import com.orderservice.service.OrderService;
import com.orderservice.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{Id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long Id) {
        return Optional.of(ResponseEntity.ok(orderService.getOrderById(Id)))
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @Validated @RequestBody OrderDTO orderDTO) {
        OrderDTO existingOrder = orderService.getOrderById(id);
        if (existingOrder != null) {
            orderService.updateOrder(id, orderDTO);
            return ResponseEntity.created(URI.create("/orders/" + id)).build();
        } else {
            throw new NotFoundException("Order not found");
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long Id) {
        OrderDTO existingOrder = orderService.getOrderById(Id);
        if (existingOrder != null) {
            orderService.deleteOrder(Id);
            return ResponseEntity.noContent().build();
        } else {
            throw new IllegalArgumentException("Order not found");
        }
    }
}