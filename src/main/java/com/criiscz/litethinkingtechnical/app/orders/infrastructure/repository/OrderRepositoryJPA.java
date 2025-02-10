package com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.orders.infrastructure.repository.dto.OrderDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface OrderRepositoryJPA extends JpaRepository<OrderDTO, Long>, JpaSpecificationExecutor<OrderDTO> {
}
