package com.blanc.market.domain.ingredient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
public class IngredientDto {

    private Long id;
    private String name;
    private Long risk;
}
