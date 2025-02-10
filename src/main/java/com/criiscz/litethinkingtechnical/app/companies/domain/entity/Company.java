package com.criiscz.litethinkingtechnical.app.companies.domain.entity;

import lombok.Builder;

@Builder
public record Company(
        String NIT,
        String name,
        String address,
        String phone
) {
}
