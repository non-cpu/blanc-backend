package com.blanc.market.domain.cart.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private Long userId;
    private Long productId;
    private int productQuantity;
}
