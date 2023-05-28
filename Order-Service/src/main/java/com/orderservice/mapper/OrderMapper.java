package com.orderservice.mapper;

import com.orderservice.entity.Order;
import com.orderservice.model.OrderDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);
}
