package com.orderservice.bootstrap;

import com.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoad implements CommandLineRunner {

    private final OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

//        Order order1 = Order.builder()
//                .orderNumber("Order #1")
//                .itemsList(Arrays.asList(OrderLineItem.builder()
//                                .itemCode("Item A")
//                                .price(BigDecimal.valueOf(10.0))
//                                .quantity(1)
//                                .build(),
//                        OrderLineItem.builder()
//                                .itemCode("Item B")
//                                .price(BigDecimal.valueOf(5.0))
//                                .quantity(3)
//                                .build()))
//                .build();
//
//        Order order2 = Order.builder()
//                .orderNumber("Order #2")
//                .itemsList(Arrays.asList(OrderLineItem.builder()
//                                .itemCode("Item B")
//                                .price(BigDecimal.valueOf(5.0))
//                                .quantity(2)
//                                .build(),
//                        OrderLineItem.builder()
//                                .itemCode("Item C")
//                                .price(BigDecimal.valueOf(2.0))
//                                .quantity(4)
//                                .build()))
//                .build();
//
//        Order order3 = Order.builder()
//                .orderNumber("Order #3")
//                .itemsList(Arrays.asList(OrderLineItem.builder()
//                                .itemCode("Item D")
//                                .price(BigDecimal.valueOf(10.0))
//                                .quantity(100)
//                                .build(),
//                        OrderLineItem.builder()
//                                .itemCode("Item E")
//                                .price(BigDecimal.valueOf(5.0))
//                                .quantity(20)
//                                .build()))
//                .build();
//
//        orderRepository.saveAll(Arrays.asList(order1, order2, order3));
        log.info("Orders in repository: " + orderRepository.count());
    }
}
