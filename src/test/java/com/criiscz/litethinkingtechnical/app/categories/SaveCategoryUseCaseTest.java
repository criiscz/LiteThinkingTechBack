package com.criiscz.litethinkingtechnical.app.categories;

import com.criiscz.litethinkingtechnical.app.categories.application.SaveCategoryUseCase;
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
class SaveCategoryUseCaseTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private SaveCategoryUseCase saveCategoryUseCase;

    @Test
    void execute_ShouldSaveAndReturnCategory_WhenInputIsValid() {
        // Arrange
        CategoryInput categoryInput = new CategoryInput("New Category");
        Category mockCategory = new Category(1L, "New Category");

        when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

        // Act
        CategoryOutput result = saveCategoryUseCase.execute(categoryInput);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("New Category", result.name());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void execute_ShouldThrowException_WhenRepositoryFails() {
        // Arrange
        CategoryInput categoryInput = new CategoryInput("New Category");

        when(categoryRepository.save(any(Category.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> saveCategoryUseCase.execute(categoryInput));
        assertEquals("Database error", exception.getMessage());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }
}
