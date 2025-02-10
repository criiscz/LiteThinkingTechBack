package com.criiscz.litethinkingtechnical.app.products.infrastructure.repository.dto;

import com.criiscz.litethinkingtechnical.app.categories.infrastructure.repository.dto.CategoryDTO;
import com.criiscz.litethinkingtechnical.app.companies.infrastructure.repository.dto.CompanyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class ProductDTO {

    @Id
    private String code;
    private String name;
    private String characteristics;
    private Double price;
    private String currency;
    private Long stock;

    @JoinColumn(name = "company_nit")
    @ManyToOne(fetch = FetchType.LAZY)
    private CompanyDTO company;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_code"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryDTO> categories;

}
