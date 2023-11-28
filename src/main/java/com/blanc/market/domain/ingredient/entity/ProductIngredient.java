package com.blanc.market.domain.ingredient.entity;

import com.blanc.market.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ProductIngredient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Builder
    public ProductIngredient(Product product, Ingredient ingredient) {
        this.product = product;
        this.ingredient = ingredient;
    }

    public void changeProduct(Product product) {
        this.product = product;
    }
}
