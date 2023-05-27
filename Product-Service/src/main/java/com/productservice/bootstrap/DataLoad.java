package com.productservice.bootstrap;

import com.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class DataLoad implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

//        Product product1 = Product.builder()
//                .name("Product 1")
//                .description("Description 1")
//                .price(new BigDecimal("10.99"))
//                .build();
//
//        Product product2 = Product.builder()
//                .name("Product 2")
//                .description("Description 2")
//                .price(new BigDecimal("19.99"))
//                .build();
//
//        Product product3 = Product.builder()
//                .name("Product 3")
//                .description("Description 3")
//                .price(new BigDecimal("5.99"))
//                .build();
//
//        Product product4 = Product.builder()
//                .name("Product 4")
//                .description("Description 4")
//                .price(new BigDecimal("15.99"))
//                .build();
//
//        Product product5 = Product.builder()
//                .name("Product 5")
//                .description("Description 5")
//                .price(new BigDecimal("8.99"))
//                .build();
//
//        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5));

        log.info("products in product repository: " + productRepository.count());
    }
}
