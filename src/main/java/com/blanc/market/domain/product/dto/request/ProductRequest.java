package com.blanc.market.domain.product.dto.request;

import com.blanc.market.domain.ingredient.dto.IngredientRequest;
import com.blanc.market.domain.ingredient.entity.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private double price;
    private String description;
    private Set<IngredientRequest> ingredients;
}
