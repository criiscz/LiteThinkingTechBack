package com.criiscz.litethinkingtechnical.app.categories.ports.out;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import lombok.Builder;

@Builder
public record CategoryOutput(
        Long id,
        String name
) {

    public static CategoryOutput of(Category category) {
        return CategoryOutput.builder()
                .id(category.id())
                .name(category.name())
                .build();
    }

    public static Category to(CategoryOutput categoryOutput) {
        return Category.builder()
                .id(categoryOutput.id())
                .name(categoryOutput.name())
                .build();
    }
}
