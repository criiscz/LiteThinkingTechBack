package com.criiscz.litethinkingtechnical.app.categories.application;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.ports.in.CategoryInput;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutput;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

@UseCase
@AllArgsConstructor
public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryOutput execute(Long categoryId, CategoryInput categoryInput) {
        Category category = CategoryInput.to(categoryInput);
        Category updatedCategory = categoryRepository.update(categoryId, category);
        return CategoryOutput.of(updatedCategory);
    }
}
