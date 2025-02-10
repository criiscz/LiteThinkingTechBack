package com.criiscz.litethinkingtechnical.app.categories.domain.repository;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(Long id);
    List<Category> findAll();
    boolean delete(Long id);
    Category update(Long id, Category category);
}
