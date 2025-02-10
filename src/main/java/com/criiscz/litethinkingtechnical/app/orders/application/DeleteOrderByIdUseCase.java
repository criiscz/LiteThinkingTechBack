package com.criiscz.litethinkingtechnical.app.orders.application;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.domain.repository.OrderRepository;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Map;

@UseCase
@AllArgsConstructor
public class DeleteOrderByIdUseCase {

    private final OrderRepository orderRepository;

    public boolean execute(Long id) {
        var order = orderRepository.getOrderById(id).orElseThrow(
                () -> new ItemNotFoundException("Order not found")
        );
        return orderRepository.deleteOrder(order.id());
    }
}
