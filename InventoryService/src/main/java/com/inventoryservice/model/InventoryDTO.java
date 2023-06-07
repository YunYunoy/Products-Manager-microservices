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

    @NotBlank
    @Size(max = 255)
    private String itemCode;

    @Min(0)
    @Builder.Default
    private Integer quantity = 0;
}
