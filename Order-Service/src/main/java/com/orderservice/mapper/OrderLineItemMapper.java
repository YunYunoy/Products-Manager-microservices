package com.orderservice.mapper;

import com.orderservice.entity.OrderLineItem;
import com.orderservice.model.OrderLineItemDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderLineItemMapper {

    OrderLineItem toEntity(OrderLineItemDTO orderLineItemDTO);

    OrderLineItemDTO toDTO(OrderLineItem orderLineItem);
}
