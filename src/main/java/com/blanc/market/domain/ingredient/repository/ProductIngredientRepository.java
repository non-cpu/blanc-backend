package com.blanc.market.domain.ingredient.repository;

import com.blanc.market.domain.ingredient.entity.ProductIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {
}
