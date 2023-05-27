package com.productservice.mapper;

import com.productservice.entity.Product;
import com.productservice.model.ProductDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProductMapper {

    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
