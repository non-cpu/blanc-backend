package com.blanc.market.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartRequest {
    private int productQuantity;
    private Long userId;
    private Long productId;
}
