package com.orderservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderLineItemDTO {

    private Long id;

    private String itemCode;

    @Positive
    @NotBlank
    private BigDecimal price;

    @Positive
    @NotBlank
    private Integer quantity;
}
