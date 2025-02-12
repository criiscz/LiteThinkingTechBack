package com.criiscz.litethinkingtechnical.app.categories;

import com.criiscz.litethinkingtechnical.app.categories.application.GetAllCategoriesUseCase;
import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutputList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllCategoriesUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetAllCategoriesUseCase getAllCategoriesUseCase;

    @Test
    void execute_ShouldReturnCategoryList_WhenCategoriesExist() {
        // Arrange
        List<Category> mockCategories = Arrays.asList(
                new Category(1L, "Category 1"),
                new Category(2L, "Category 2")
        );

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        // Act
        CategoryOutputList result = getAllCategoriesUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.categories().size());
        assertEquals("Category 1", result.categories().get(0).name());
        assertEquals("Category 2", result.categories().get(1).name());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void execute_ShouldReturnEmptyList_WhenNoCategoriesExist() {
        // Arrange
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        CategoryOutputList result = getAllCategoriesUseCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.categories().isEmpty());

        verify(categoryRepository, times(1)).findAll();
    }
}
