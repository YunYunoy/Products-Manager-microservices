package com.productservice.controller;

import com.productservice.model.ProductDTO;
import com.productservice.service.ProductService;
import com.productservice.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(@RequestParam List<String> names) {
        return ResponseEntity.ok(productService.getProducts(names));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ProductDTO> getProductById(@PathVariable("id") String id) {
        return Optional.ofNullable(productService.getProductById(id)
                .orElseThrow(NotFoundException::new));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@Validated @RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable("name") String name, @Validated @RequestBody ProductDTO productDTO) {
        Optional<ProductDTO> existingProductDTO = productService.getProductByName(name);
        if (existingProductDTO.isPresent()) {
            return productService.updateProduct(name, productDTO);
        } else {
            throw new NotFoundException("Product not found");
        }
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("name") String name) {
        Optional<ProductDTO> existingProductDTO = productService.getProductByName(name);
        if (existingProductDTO.isPresent()) {
            productService.deleteProductByName(name);
        } else {
            throw new NotFoundException();
        }
    }

}
