package com.criiscz.litethinkingtechnical.app.categories;

import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.application.DeleteCategoryUseCase;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
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
class DeleteCategoryUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DeleteCategoryUseCase deleteCategoryUseCase;

    @Test
    void execute_ShouldDeleteCategory_WhenCategoryExists() {
        // Arrange
        Long categoryId = 1L;
        Category mockCategory = new Category(categoryId, "Test Category");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));
        when(categoryRepository.delete(categoryId)).thenReturn(true);

        // Act
        boolean result = deleteCategoryUseCase.execute(categoryId);

        // Assert
        assertTrue(result);
        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, times(1)).delete(categoryId);
    }

    @Test
    void execute_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act & Assert
        ItemNotFoundException exception = assertThrows(ItemNotFoundException.class, () -> deleteCategoryUseCase.execute(categoryId));
        assertEquals("Category not found", exception.getMessage());

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(categoryRepository, never()).delete(anyLong());
    }
}
