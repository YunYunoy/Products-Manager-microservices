package com.productservice.service;

import com.productservice.entity.Product;
import com.productservice.mapper.ProductMapper;
import com.productservice.model.InventoryDTO;
import com.productservice.model.ProductDTO;
import com.productservice.repository.ProductRepository;
import com.productservice.utils.NotFoundException;
import com.productservice.web.WebHandler;
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
    private final WebHandler webHandler;


    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    public Optional<ProductDTO> getProductByName(String name) {
        return productRepository.findByName(name)
                .map(productMapper::toDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            webHandler.createInventory(new InventoryDTO(productDTO.getName()));
        } catch (Exception ex) {
            throw new IllegalArgumentException("Inventory already exists", ex);
        }

        return productMapper.toDTO(productRepository.save(productMapper.toEntity(productDTO)));
    }

    public ProductDTO updateProduct(String name, ProductDTO productDTO) {
        Optional<Product> existingProductOptional = productRepository.findByName(name);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(productDTO.getName());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setPrice(productDTO.getPrice());

            webHandler.updateInventory(name, new InventoryDTO(productDTO.getName())).block();

            return productMapper.toDTO(productRepository.save(existingProduct));
        }
        throw new NotFoundException(name);
    }

    public void deleteProductByName(String name) {
        try {
            webHandler.deleteInventory(name).block();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Ups... something went wrong: " + ex);
        }
        productRepository.deleteByName(name);
    }


}
