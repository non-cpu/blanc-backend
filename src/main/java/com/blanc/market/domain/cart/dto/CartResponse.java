package com.blanc.market.domain.cart.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class CartResponse {
    private Long id;
    private int productQuantity;
    private Long productId;
}
