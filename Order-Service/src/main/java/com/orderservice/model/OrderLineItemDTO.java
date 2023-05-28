package com.orderservice.model;

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
    private BigDecimal price;
    private Integer quantity;
}
