package com.criiscz.litethinkingtechnical.app.categories.application;

import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutputList;
import com.criiscz.litethinkingtechnical.common.UseCase;
import lombok.AllArgsConstructor;

@UseCase
public class GetAllCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public GetAllCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryOutputList execute() {
        return CategoryOutputList.of(categoryRepository.findAll());
    }

}
