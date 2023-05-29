package com.productservice.service;

import com.productservice.entity.Product;
import com.productservice.mapper.ProductMapper;
import com.productservice.model.ProductDTO;
import com.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        return productMapper.toDTO(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {

        Product updatedProduct = Product.builder()
                .id(id)
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();

        return productMapper.toDTO(productRepository.save(updatedProduct));
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
