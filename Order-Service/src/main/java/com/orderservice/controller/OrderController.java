package com.orderservice.controller;

import com.orderservice.model.OrderDTO;
import com.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(orderService.getOrderById(Id));

    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Validated @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateOrder(@PathVariable Long id, @Validated @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(id, orderDTO);
        return "Order has been updated";
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long Id) {
        orderService.deleteOrder(Id);
        return ResponseEntity.noContent().build();
    }
}