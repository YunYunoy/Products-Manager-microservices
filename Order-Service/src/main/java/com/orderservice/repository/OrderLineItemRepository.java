package com.orderservice.repository;

import com.orderservice.entity.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem,Long> {
}
