package com.criiscz.litethinkingtechnical.app.categories.infrastructure.controller;

import com.criiscz.litethinkingtechnical.app.categories.application.*;
import com.criiscz.litethinkingtechnical.app.categories.ports.in.CategoryInput;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutput;
import com.criiscz.litethinkingtechnical.app.categories.ports.out.CategoryOutputList;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Categories", description = "Categories API")
public class CategoryController {

    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final SaveCategoryUseCase saveCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryOutput> getCategoryById(
            @PathVariable(value = "categoryId") Long categoryId
    ) {
        return ResponseEntity.ok(getCategoryByIdUseCase.execute(categoryId));
    }

    @GetMapping("/")
    public ResponseEntity<CategoryOutputList> getAllCategories() {
        return ResponseEntity.ok(getAllCategoriesUseCase.execute());
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(
            @PathVariable(value = "categoryId") Long categoryId
    ) {
        return ResponseEntity.ok(deleteCategoryUseCase.execute(categoryId));
    }

    @PostMapping("/")
    public ResponseEntity<CategoryOutput> saveCategory(
            @RequestBody CategoryInput category
    ) {
        return ResponseEntity.ok(saveCategoryUseCase.execute(category));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryOutput> updateCategory(
            @PathVariable(value = "categoryId") Long categoryId,
            @RequestBody CategoryInput category
    ) {
        return ResponseEntity.ok(updateCategoryUseCase.execute(categoryId, category));
    }

}
