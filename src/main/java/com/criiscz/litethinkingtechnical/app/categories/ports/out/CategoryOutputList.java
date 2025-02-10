package com.criiscz.litethinkingtechnical.app.categories.ports.out;

import java.util.List;
import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import lombok.Builder;

@Builder
public record CategoryOutputList(
        List<Category> categories,
        String metadata
) {

    public static CategoryOutputList of(List<Category> categories, String metadata) {
        return CategoryOutputList.builder()
                .categories(categories)
                .metadata(metadata)
                .build();
    }

    public static CategoryOutputList of(List<Category> categories) {
        return CategoryOutputList.builder()
                .categories(categories)
                .metadata("No metadata")
                .build();
    }

    public static List<Category> to(CategoryOutputList categoryOutputList) {
        return categoryOutputList.categories();
    }


}
