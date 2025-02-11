package com.criiscz.litethinkingtechnical.app.orderProducts.ports.in;


public record OrderProductInput(
        String productCode,
        Integer quantity,
        Double price
) {
}
