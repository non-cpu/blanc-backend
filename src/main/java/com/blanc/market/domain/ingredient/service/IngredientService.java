package com.blanc.market.domain.ingredient.service;

import com.blanc.market.domain.ingredient.repository.IngredientRepository;
import com.blanc.market.domain.ingredient.repository.ProductIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductIngredientRepository productIngredientRepository;

}
