package com.inventoryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryDTO {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Item code is required")
    @Size(max = 255, message = "Item code must be at most 255 characters")
    private String itemCode;

    @Min(value = 0, message = "Quantity must be a positive or zero value")
    @Builder.Default
    private Integer quantity = 0;
}
