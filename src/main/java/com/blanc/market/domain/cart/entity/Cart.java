package com.blanc.market.domain.cart.entity;

import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.user.entity.User;
import com.blanc.market.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne
    private Product product;

    private int productQuantity;

    public void updateProductQuantity(int quantity) {
        this.productQuantity = quantity;
    }
}
