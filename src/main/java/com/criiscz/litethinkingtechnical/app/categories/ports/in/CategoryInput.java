package com.criiscz.litethinkingtechnical.app.categories.ports.in;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;

public record CategoryInput(
        String name
) {
    public static Category to(CategoryInput categoryInput) {
        return Category.builder()
                .name(categoryInput.name())
                .build();
    }

    public static CategoryInput of(String name) {
        return new CategoryInput(name);
    }
}
