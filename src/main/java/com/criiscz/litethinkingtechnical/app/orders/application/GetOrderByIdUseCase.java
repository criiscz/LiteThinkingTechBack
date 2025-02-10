package com.criiscz.litethinkingtechnical.app.orders.application;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.domain.repository.OrderRepository;
import com.criiscz.litethinkingtechnical.app.orders.ports.out.OrderOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.Map;

@UseCase
@AllArgsConstructor
public class GetOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public OrderOutput execute(Long id) {
        Order order = orderRepository.getOrderById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderOutput.fromOrder(order);

    }
}
