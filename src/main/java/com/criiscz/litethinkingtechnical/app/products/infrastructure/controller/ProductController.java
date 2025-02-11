package com.criiscz.litethinkingtechnical.app.products.infrastructure.controller;

import com.criiscz.litethinkingtechnical.app.products.application.CreateNewProductUseCase;
import com.criiscz.litethinkingtechnical.app.products.application.DeleteProductUseCase;
import com.criiscz.litethinkingtechnical.app.products.application.GetAllProductsUseCase;
import com.criiscz.litethinkingtechnical.app.products.application.GetProductByIdUseCase;
import com.criiscz.litethinkingtechnical.app.products.domain.entity.Product;
import com.criiscz.litethinkingtechnical.app.products.ports.in.ProductInput;
import com.criiscz.litethinkingtechnical.app.products.ports.out.ProductOutput;
import com.criiscz.litethinkingtechnical.common.Entity.ResponseWithPaginationData;
import com.criiscz.litethinkingtechnical.common.utils.AppConstants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Product", description = "The Product API")
public class ProductController {

    private final CreateNewProductUseCase createNewProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @GetMapping("/")
    public ResponseEntity<ResponseWithPaginationData<Product>> getAllProducts(
            @RequestParam(value = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) Long categoryId
    ) {
        Map<String, Object> filters = buildFilters(name, categoryId);
        return ResponseEntity.ok(getAllProductsUseCase.execute(page, size, filters));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductOutput>> getAllProducts() {
        return ResponseEntity.ok(getAllProductsUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutput> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(getProductByIdUseCase.execute(id));
    }

    @PostMapping("/")
    public ResponseEntity<ProductOutput> createProduct(@RequestBody ProductInput product) {
        return ResponseEntity.ok(createNewProductUseCase.execute(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok(deleteProductUseCase.execute(id));
    }

    private Map<String, Object> buildFilters(String name, Long categoryId) {
        Map<String, Object> filters = new HashMap<>();
        if (name != null) filters.put("name", name);
        if (categoryId != null) filters.put("category_id", categoryId);

        return filters;
    }

}
