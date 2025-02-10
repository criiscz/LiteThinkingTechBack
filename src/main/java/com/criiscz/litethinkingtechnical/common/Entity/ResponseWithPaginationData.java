package com.criiscz.litethinkingtechnical.common.Entity;

import java.util.List;

public record ResponseWithPaginationData<T>(
        List<T> data,
        Pagination pagination
) {
}
