package com.orderservice.model;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private BigDecimal price;

    @Positive
    @NotNull
    private Integer quantity;
}
