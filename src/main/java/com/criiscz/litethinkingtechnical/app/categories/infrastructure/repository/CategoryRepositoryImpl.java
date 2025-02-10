package com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.dto.CategoryDTO;
import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.mapper.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryRepositoryJPA categoryRepositoryJPA;

    @Override
    public Category save(Category category) {
        return CategoryMapper.toDomain(categoryRepositoryJPA.save(CategoryMapper.toDTO(category)));
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepositoryJPA.findById(id).map(CategoryMapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepositoryJPA.findAll().stream().map(CategoryMapper::toDomain).toList();
    }

    @Override
    public boolean delete(Long id) {
        return categoryRepositoryJPA.findById(id).map(category -> {
            categoryRepositoryJPA.delete(category);
            return true;
        }).orElse(false);
    }

    @Override
    public Category update(Long id, Category category) {
        CategoryDTO categoryDTO = categoryRepositoryJPA.findById(id).orElseThrow();
        categoryDTO.setName(category.name());
        return CategoryMapper.toDomain(categoryRepositoryJPA.save(categoryDTO));
    }
}
