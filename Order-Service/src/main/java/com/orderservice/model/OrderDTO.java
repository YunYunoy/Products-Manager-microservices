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

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @NotEmpty(message = "Order items list cannot be empty")
    @Valid
    private List<OrderLineItemDTO> itemsList;
}

