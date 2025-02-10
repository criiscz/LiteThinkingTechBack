package com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.dto.OrderDTO;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.id())
                .build();
    }

    public static Order toDomain(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .build();
    }
}
