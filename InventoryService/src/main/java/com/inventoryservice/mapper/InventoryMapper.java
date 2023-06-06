package com.inventoryservice.mapper;

import com.inventoryservice.entity.Inventory;
import com.inventoryservice.model.InventoryDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface InventoryMapper {

    Inventory toEntity(InventoryDTO inventoryDTO);

    InventoryDTO toDTO(Inventory inventory);
}
