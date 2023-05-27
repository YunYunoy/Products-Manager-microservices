package com.productservice.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO {

    private String id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;
    private String description;
    @Positive
    private BigDecimal price;
}

