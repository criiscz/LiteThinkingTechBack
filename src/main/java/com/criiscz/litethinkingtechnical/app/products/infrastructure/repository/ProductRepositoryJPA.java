package com.criiscz.litethinkingtechnical.app.products.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface ProductRepositoryJPA extends JpaRepository<ProductDTO, String>, JpaSpecificationExecutor<ProductDTO> {
}
