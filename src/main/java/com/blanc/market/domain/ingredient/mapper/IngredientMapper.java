package com.blanc.market.domain.ingredient.mapper;

import com.blanc.market.domain.ingredient.dto.IngredientDto;
import com.blanc.market.domain.ingredient.dto.IngredientRequest;
import com.blanc.market.domain.ingredient.entity.Ingredient;
import com.blanc.market.domain.ingredient.entity.ProductIngredient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    Ingredient toEntity(IngredientRequest ingredientRequest);

    @Mapping(source = "ingredient", target = ".")
    IngredientDto from(ProductIngredient productIngredient);
}
