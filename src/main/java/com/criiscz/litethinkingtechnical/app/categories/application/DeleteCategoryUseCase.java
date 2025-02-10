package com.criiscz.litethinkingtechnical.app.categories.application;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;

@UseCase
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public DeleteCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public boolean execute(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ItemNotFoundException("Category not found"));
        return categoryRepository.delete(category.id());
    }

}
