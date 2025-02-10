package com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface CategoryRepositoryJPA extends JpaRepository<CategoryDTO, Long> {
}
