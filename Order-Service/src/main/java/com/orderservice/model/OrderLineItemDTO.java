package com.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrderLineItemDTO {

    @JsonIgnore
    private Long id;

    private String itemCode;

    private BigDecimal price;

    @Positive
    @NotNull
    private Integer quantity;
}
