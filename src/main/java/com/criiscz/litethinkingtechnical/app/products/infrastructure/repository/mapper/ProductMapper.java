package com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.mapper;

import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.mapper.CategoryMapper;
import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.mapper.CompanyMapper;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.dto.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .code(product.code())
                .name(product.name())
                .categories(CategoryMapper.toDTOList(product.categories()))
                .company(CompanyMapper.toDTO(product.company()))
                .characteristics(product.characteristics())
                .currency(product.currency())
                .price(product.price())
                .stock(product.stock())
                .build();
    }

    public static Product toDomain(ProductDTO productDTO) {
        return Product.builder()
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .characteristics(productDTO.getCharacteristics())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .currency(productDTO.getCurrency())
                .company(CompanyMapper.toDomain(productDTO.getCompany()))
                .categories(CategoryMapper.toDomainList(productDTO.getCategories()))
                .build();
    }
}
