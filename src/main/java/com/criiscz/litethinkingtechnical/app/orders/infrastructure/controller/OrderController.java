package com.criiscz.litethinkingtechnical.app.orders.infrastructure.controller;

import com.criiscz.litethinkingtechnical.app.orders.application.CreateOrderUseCase;
import com.criiscz.litethinkingtechnical.app.orders.application.DeleteOrderByIdUseCase;
import com.criiscz.litethinkingtechnical.app.orders.application.GetAllOrdersUseCase;
import com.criiscz.litethinkingtechnical.app.orders.application.GetOrderByIdUseCase;
import com.criiscz.litethinkingtechnical.app.orders.domain.entity.Order;
import com.criiscz.litethinkingtechnical.app.orders.ports.in.OrderInput;
import com.criiscz.litethinkingtechnical.app.orders.ports.out.OrderOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.utils.AppConstants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Orders", description = "The Order API")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;
    private final GetAllOrdersUseCase getAllOrdersUseCase;
    private final DeleteOrderByIdUseCase deleteOrderByIdUseCase;

    @GetMapping("/")
    public ResponseEntity<ResponseWithPaginationData<Order>> getAllOrders(
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    ) {
        return ResponseEntity.ok(getAllOrdersUseCase.execute(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderOutput> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(getOrderByIdUseCase.execute(id));
    }

    @PostMapping("/")
    public ResponseEntity<OrderOutput> createOrder(@RequestBody OrderInput order) {
        return ResponseEntity.ok(createOrderUseCase.execute(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(deleteOrderByIdUseCase.execute(id));
    }
}
