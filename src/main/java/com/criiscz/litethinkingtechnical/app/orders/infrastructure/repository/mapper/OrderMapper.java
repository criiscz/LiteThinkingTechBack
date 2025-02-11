package com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.clients.infrastructure.repository.mapper.ClientMapper;
import com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.dto.OrderProductDTO;
import com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.mapper.OrderProductMapper;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.dto.OrderDTO;

import java.util.List;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.id())
                .client(ClientMapper.toDTO(order.client()))
                .productList(
                        order.productList() != null ?
                                order.productList().stream()
                                        .map(OrderProductMapper::toDTO)
                                        .toList() :
                                List.of()
                ).build();
    }

    public static Order toDomain(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getId())
                .client(ClientMapper.toDomain(orderDTO.getClient()))
                .productList(
                        orderDTO.getProductList() != null ?
                                orderDTO.getProductList().stream()
                                        .map(OrderProductMapper::toDomain)
                                        .toList() :
                                List.of()
                ).build();
    }
}
