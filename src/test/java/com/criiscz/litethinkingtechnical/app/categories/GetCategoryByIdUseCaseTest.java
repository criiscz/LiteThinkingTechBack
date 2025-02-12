package com.criiscz.litethinkingtechnical.app.categories.application;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutput;
import com.criiscz.litethinkingtechnical.common.exception.ItemNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCategoryByIdUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoryByIdUseCase getCategoryByIdUseCase;

    @Test
    void execute_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange
        Long categoryId = 1L;
        Category mockCategory = new Category(categoryId, "Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

        // Act
        CategoryOutput result = getCategoryByIdUseCase.execute(categoryId);

        // Assert
        assertNotNull(result);
        assertEquals(categoryId, result.id());
        assertEquals("Test Category", result.name());

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void execute_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> getCategoryByIdUseCase.execute(categoryId));
        assertEquals("Category  not found", exception.getMessage());

        verify(categoryRepository, times(1)).findById(categoryId);
    }
}
