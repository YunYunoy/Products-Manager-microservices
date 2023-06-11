package com.orderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Item code is required")
    @Size(min = 3, max = 255, message = "Item code must be between 3 and 255 characters")
    private String itemCode;

    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be a positive value")
    private Integer quantity;
}
