package com.productservice.bootstrap;

import com.productservice.entity.Product;
import com.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@RequiredArgsConstructor
@Component
@Slf4j
public class DataLoad implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Product product1 = Product.builder()
                .name("iPhone_X")
                .description("Apple iPhone X, 64GB, Space Gray")
                .price(new BigDecimal("999.99"))
                .build();

        Product product2 = Product.builder()
                .name("Samsung_Galaxy_S21")
                .description("Samsung Galaxy S21, 128GB, Phantom Black")
                .price(new BigDecimal("899.99"))
                .build();

        Product product3 = Product.builder()
                .name("Sony_WH_1000XM4")
                .description("Wireless Noise-Canceling Overhead Headphones")
                .price(new BigDecimal("349.99"))
                .build();

        Product product4 = Product.builder()
                .name("Nintendo_Switch")
                .description("Nintendo Switch Console with Neon Blue and Neon Red Joy‑Con")
                .price(new BigDecimal("299.99"))
                .build();

        productRepository.deleteAll();
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));

        log.info("products in product repository: " + productRepository.count());
    }
}
