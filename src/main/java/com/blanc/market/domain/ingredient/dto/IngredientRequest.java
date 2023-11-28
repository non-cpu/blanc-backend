package com.blanc.market.domain.ingredient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientRequest {
    private String name;
    private Long risk;
}
