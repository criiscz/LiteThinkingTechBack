package com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.dto.CategoryDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.id())
                .name(category.name())
                .build();
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Category> toDomainList(List<CategoryDTO> categoryDTOs) {
        return categoryDTOs.stream()
                .map(CategoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static Category toDomain(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }
}
