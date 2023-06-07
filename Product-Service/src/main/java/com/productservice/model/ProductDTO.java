package com.productservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @JsonIgnore
    private String id;

    @NotBlank
    @Size(min = 3, max = 255)
    @NotNull
    private String name;
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;
}

