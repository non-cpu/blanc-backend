package com.blanc.market.domain.ingredient.entity;

import com.blanc.market.domain.order.entity.OrderProduct;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;
    private String name;
    private Long risk;

}
