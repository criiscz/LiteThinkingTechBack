package com.criiscz.litethinkingtechnical.app.orders.domain.repository;

import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderRepository {
    Order saveOrder(Order order);
    boolean deleteOrder(Long id);
    Optional<Order> getOrderById(Long id);
    ResponseWithPaginationData<Order> getAllOrders(int page, int size);
}
