package com.orderservice.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

    private Long id;
    private String orderNumber;
    private List<OrderLineItemDTO> itemsList;
}

