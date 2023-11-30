package com.blanc.market.domain.product.entity;

import com.blanc.market.domain.ingredient.entity.ProductIngredient;
import com.blanc.market.domain.product.dto.ProductUpdateRequest;
import com.blanc.market.domain.review.entity.Review;
import com.blanc.market.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@Where(clause = "is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private String imageUrl;
    private String description;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private Set<ProductIngredient> productIngredients;

    @OneToMany(mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews;

    private int likeCount;


    @Enumerated(EnumType.STRING)
    private Category category;

    public void update(ProductUpdateRequest request) {
        if (request.getName() != null) {
            this.name = request.getName();
        }
        if (request.getPrice() != null) {
            this.price = request.getPrice();
        }
        if (request.getImageUrl() != null) {
            this.imageUrl = request.getImageUrl();
        }
        if (request.getDescription() != null) {
            this.description = request.getDescription();
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addProductIngredient(ProductIngredient productIngredient) {
        if (productIngredients == null) {
            productIngredients = new HashSet<>();
        }

        productIngredients.add(productIngredient);
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }




}
