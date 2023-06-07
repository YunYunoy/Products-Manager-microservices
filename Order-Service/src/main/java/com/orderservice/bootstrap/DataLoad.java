package com.orderservice.bootstrap;

import com.orderservice.entity.Order;
import com.orderservice.entity.OrderLineItem;
import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoad implements CommandLineRunner {

    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        Order order1 = Order.builder()
                .description("short passionate description")
                .orderNumber(UUID.randomUUID().toString())
                .itemsList(Arrays.asList(OrderLineItem.builder()
                                .itemCode("Item_A")
                                .price(BigDecimal.valueOf(10.0))
                                .quantity(1)
                                .build(),
                        OrderLineItem.builder()
                                .itemCode("Item_C")
                                .price(BigDecimal.valueOf(5.0))
                                .quantity(3)
                                .build()))
                .build();

        Order order2 = Order.builder()
                .description("short passionate description")
                .orderNumber(UUID.randomUUID().toString())
                .itemsList(Arrays.asList(OrderLineItem.builder()
                                .itemCode("Item_B")
                                .price(BigDecimal.valueOf(5.0))
                                .quantity(2)
                                .build(),
                        OrderLineItem.builder()
                                .itemCode("Item_A")
                                .price(BigDecimal.valueOf(2.0))
                                .quantity(4)
                                .build()))
                .build();

        Order order3 = Order.builder()
                .description("short passionate description")
                .orderNumber(UUID.randomUUID().toString())
                .itemsList(Arrays.asList(OrderLineItem.builder()
                                .itemCode("Item_A")
                                .price(BigDecimal.valueOf(10.0))
                                .quantity(100)
                                .build(),
                        OrderLineItem.builder()
                                .itemCode("Item_C")
                                .price(BigDecimal.valueOf(5.0))
                                .quantity(20)
                                .build()))
                .build();


        orderRepository.deleteAll();
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        log.info("Orders in repository: " + orderRepository.count());
    }
}
