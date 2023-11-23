package com.blanc.market.domain.ingredient.service;

import com.blanc.market.domain.ingredient.entity.Ingredient;
import com.blanc.market.domain.ingredient.entity.ProductIngredient;
import com.blanc.market.domain.ingredient.repository.IngredientRepository;
import com.blanc.market.domain.ingredient.repository.ProductIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;

}
