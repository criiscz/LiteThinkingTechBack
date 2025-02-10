package com.criiscz.litethinkingtechnical.common.Entity;

import lombok.Builder;

@Builder
public record Pagination(
        long page,
        long size,
        long totalItems,
        long totalPages,
        boolean hasNext
) {
}
