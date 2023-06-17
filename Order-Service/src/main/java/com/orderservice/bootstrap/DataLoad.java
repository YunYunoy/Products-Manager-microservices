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
                .description("Order 1: iPhone X and Sony WH-1000XM4 headphones")
                .orderNumber(UUID.randomUUID().toString())
                .itemsList(Arrays.asList(OrderLineItem.builder()
                                .itemCode("iPhone_X")
                                .price(BigDecimal.valueOf(999.99))
                                .quantity(1)
                                .build(),
                        OrderLineItem.builder()
                                .itemCode("Sony_WH_1000XM4")
                                .price(BigDecimal.valueOf(349.99))
                                .quantity(3)
                                .build()))
                .build();

        Order order2 = Order.builder()
                .description("Order 2: Samsung Galaxy S21 and additional iPhone X")
                .orderNumber(UUID.randomUUID().toString())
                .itemsList(Arrays.asList(OrderLineItem.builder()
                                .itemCode("Samsung_Galaxy_S21")
                                .price(BigDecimal.valueOf(899.99))
                                .quantity(2)
                                .build(),
                        OrderLineItem.builder()
                                .itemCode("iPhone_X")
                                .price(BigDecimal.valueOf(999.99))
                                .quantity(4)
                                .build()))
                .build();

        Order order3 = Order.builder()
                .description("Order 3: Bulk order of iPhone X and Nintendo Switch Console with Neon Blue")
                .orderNumber(UUID.randomUUID().toString())
                .itemsList(Arrays.asList(OrderLineItem.builder()
                                .itemCode("iPhone_X")
                                .price(BigDecimal.valueOf(999.99))
                                .quantity(2)
                                .build(),
                        OrderLineItem.builder()
                                .itemCode("Nintendo_Switch")
                                .price(BigDecimal.valueOf(299.99))
                                .quantity(1)
                                .build()))
                .build();


        orderRepository.deleteAll();
        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        log.info("Orders in repository: " + orderRepository.count());
    }
}
