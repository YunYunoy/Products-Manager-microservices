package com.orderservice.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderDTO {

    private Long id;
    private String orderNumber;

    @Size(max = 255)
    private String description;

    @NotEmpty
    @Valid
    private List<OrderLineItemDTO> itemsList;
}

