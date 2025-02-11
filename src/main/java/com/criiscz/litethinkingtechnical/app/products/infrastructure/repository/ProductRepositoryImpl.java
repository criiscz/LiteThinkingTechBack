package com.criiscz.litethinkingtechnical.app.products.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.mapper.CategoryMapper;
import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.mapper.CompanyMapper;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.domain.repository.ProductRepository;
import com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.dto.ProductDTO;
import com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.mapper.ProductMapper;
import com.criiscz.litethinkingtechnical.common.Entity.Pagination;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductRepositoryJPA productRepositoryjpa;

    @Override
    public Product save(Product product) {
        return ProductMapper.toDomain(productRepositoryjpa.save(ProductMapper.toDTO(product)));
    }

    @Override
    public Optional<Product> findById(String code) {
        return productRepositoryjpa.findById(code).map(ProductMapper::toDomain);
    }

    @Override
    public boolean deleteById(String code) {
        var product = productRepositoryjpa.findById(code);
        if (product.isPresent()) {
            productRepositoryjpa.delete(product.get());
            return true;
        }
        return false;
    }

    @Override
    public ResponseWithPaginationData<Product> findAll(int page, int size, Map<String, Object> filters) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<ProductDTO> specification = buildSpecification(filters);
        var products = productRepositoryjpa.findAll(specification, pageable);
        return new ResponseWithPaginationData<>(
                products.getContent().stream().map(ProductMapper::toDomain).toList(),
                Pagination.builder()
                        .page(page)
                        .size(size)
                        .totalItems(products.getTotalElements())
                        .totalPages(products.getTotalPages())
                        .hasNext(products.hasNext())
                        .build());
    }

    @Override
    public List<Product> findAll() {
        return productRepositoryjpa.findAll().stream().map(ProductMapper::toDomain).toList();
    }

    @Override
    public Product update(String id, Product product) {
        ProductDTO productDTO = productRepositoryjpa.findById(id).orElseThrow();
        productDTO.setName(product.name());
        productDTO.setCharacteristics(product.characteristics());
        productDTO.setCurrency(product.currency());
        productDTO.setStock(product.stock());
        productDTO.setCompany(CompanyMapper.toDTO(product.company()));
        productDTO.setCategories(product.categories().stream().map(CategoryMapper::toDTO).toList());
        productDTO.setPrice(product.price());
        return ProductMapper.toDomain(productRepositoryjpa.save(productDTO));
    }

    private Specification<ProductDTO> buildSpecification(Map<String, Object> filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach((key, value) -> {
                Predicate predicate = switch (key) {
                    case "name" -> criteriaBuilder.like(root.get("name"), "%" + value + "%");
                    case "category_id" ->
                            criteriaBuilder.equal(root.get("category_id"), value); // TODO: Revisar si se puede sacar varias categorias
                    default -> null;
                };
                if (predicate != null) {
                    predicates.add(predicate);
                }
            });
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
