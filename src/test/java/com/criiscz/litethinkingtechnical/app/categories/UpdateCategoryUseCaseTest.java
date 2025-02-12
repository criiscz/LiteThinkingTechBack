package com.criiscz.litethinkingtechnical.app.categories;

import com.criiscz.litethinkingtechnical.app.categories.application.UpdateCategoryUseCase;
import com.criiscz.litethinkingtechnical.app.categories.domain.entity.Category;
import com.criiscz.litethinkingtechnical.app.categories.domain.repository.CategoryRepository;
import com.criiscz.litethinkingtechnical.app.categories.ports.in.CategoryInput;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateCategoryUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private UpdateCategoryUseCase updateCategoryUseCase;

    @Test
    void execute_ShouldUpdateAndReturnCategory_WhenCategoryExists() {
        // Arrange
        Long categoryId = 1L;
        CategoryInput categoryInput = new CategoryInput("Updated Category");
        Category mockUpdatedCategory = new Category(categoryId, "Updated Category");

        when(categoryRepository.update(eq(categoryId), any(Category.class))).thenReturn(mockUpdatedCategory);

        // Act
        CategoryOutput result = updateCategoryUseCase.execute(categoryId, categoryInput);

        // Assert
        assertNotNull(result);
        assertEquals(categoryId, result.id());
        assertEquals("Updated Category", result.name());

        verify(categoryRepository, times(1)).update(eq(categoryId), any(Category.class));
    }

    @Test
    void execute_ShouldThrowException_WhenCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        CategoryInput categoryInput = new CategoryInput("Updated Category");

        when(categoryRepository.update(eq(categoryId), any(Category.class)))
                .thenThrow(new RuntimeException("Category not found"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> updateCategoryUseCase.execute(categoryId, categoryInput));
        assertEquals("Category not found", exception.getMessage());

        verify(categoryRepository, times(1)).update(eq(categoryId), any(Category.class));
    }

    @Test
    void execute_ShouldThrowException_WhenRepositoryFails() {
        // Arrange
        Long categoryId = 1L;
        CategoryInput categoryInput = new CategoryInput("Updated Category");

        when(categoryRepository.update(eq(categoryId), any(Category.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> updateCategoryUseCase.execute(categoryId, categoryInput));
        assertEquals("Database error", exception.getMessage());

        verify(categoryRepository, times(1)).update(eq(categoryId), any(Category.class));
    }
}
