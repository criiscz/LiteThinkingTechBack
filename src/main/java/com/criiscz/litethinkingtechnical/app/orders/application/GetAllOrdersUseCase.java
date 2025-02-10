package com.criiscz.litethinkingtechnical.app.orders.application;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.domain.repository.OrderRepository;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

import java.util.Map;

@UseCase
@AllArgsConstructor
public class GetAllOrdersUseCase {

    private final OrderRepository orderRepository;

    public ResponseWithPaginationData<Order> execute(int page, int size) {
        return orderRepository.getAllOrders(page, size);
    }
}
