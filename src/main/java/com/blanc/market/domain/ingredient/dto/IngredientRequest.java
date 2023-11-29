package com.blanc.market.domain.ingredient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class IngredientRequest {
    private String name;
    private Long risk;
}
