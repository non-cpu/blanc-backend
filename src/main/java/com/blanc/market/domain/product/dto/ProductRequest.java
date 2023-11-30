package com.blanc.market.domain.product.dto;

import com.blanc.market.domain.ingredient.dto.IngredientRequest;
import com.blanc.market.domain.product.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ProductRequest {
    @NotBlank @Size(min = 1, max = 20, message = "test")
    private String name;
    private int price;
    private String description;
    private Set<IngredientRequest> ingredients;
    private Category category;
}
