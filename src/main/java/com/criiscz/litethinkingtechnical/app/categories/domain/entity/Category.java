package com.criiscz.litethinkingtechnical.app.categories.domain.entity;

import lombok.Builder;

@Builder
public record Category(
        Long id,
        String name
) {

}
