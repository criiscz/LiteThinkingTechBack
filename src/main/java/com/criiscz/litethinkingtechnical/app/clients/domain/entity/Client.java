package com.criiscz.litethinkingtechnical.app.clients.domain.entity;

import lombok.Builder;

@Builder
public record Client(
        String id,
        String name,
        String phone
) {
}
