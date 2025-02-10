package com.criiscz.litethinkingtechnical.app.categories.application;

import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class GetCategoryByIdUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryOutput execute(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryOutput::of)
                .orElseThrow(() -> new ItemNotFoundException("Category  not found"));
    }

}
