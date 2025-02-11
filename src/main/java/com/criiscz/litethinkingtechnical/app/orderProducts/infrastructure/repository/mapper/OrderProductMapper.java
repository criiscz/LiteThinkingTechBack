package com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;
import com.criiscz.litethinkingtechnical.app.orderProducts.infrastructure.repository.dto.OrderProductDTO;
import com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.mapper.OrderMapper;
import com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.mapper.ProductMapper;

public class OrderProductMapper {

    public static OrderProductDTO toDTO(OrderProduct orderProduct) {
        return OrderProductDTO.builder()
                .id(orderProduct.id())
                .orderId(orderProduct.orderId())
                .product(orderProduct.productCode())
                .quantity(orderProduct.quantity())
                .price(orderProduct.price())
                .build();
    }

    public static OrderProduct toDomain(OrderProductDTO orderProductDTO) {
        return new OrderProduct(
                orderProductDTO.getId(),
                orderProductDTO.getOrderId(),
                orderProductDTO.getProduct(),
                orderProductDTO.getQuantity(),
                orderProductDTO.getPrice()
        );
    }
}
