package com.criiscz.litethinkingtechnical.app.orderProducts.ports.out;

import com.criiscz.litethinkingtechnical.app.orderProducts.domain.entity.OrderProduct;
import com.criiscz.litethinkingtechnical.app.orders.ports.out.OrderOutput;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;

public record OrderProductOutput(
        Long id,
        String productId,
        Integer quantity,
        Double price
) {

    public static OrderProductOutput fromOrderProduct(OrderProduct orderProductOutput) {
        return new OrderProductOutput(
                orderProductOutput.id(),
                orderProductOutput.productCode(),
                orderProductOutput.quantity(),
                orderProductOutput.price()
        );
    }

    public static OrderProduct toOrderProduct(OrderProductOutput orderProductOutput) {
        return new OrderProduct(
                orderProductOutput.id(),
                0L,
                orderProductOutput.productId(),
                orderProductOutput.quantity(),
                orderProductOutput.price()
        );
    }
}
