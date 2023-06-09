package com.productservice.repository;

import com.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByName(String name);

    void deleteByName(String name);

    List<Product> findByNameIn(List<String> name);
}
