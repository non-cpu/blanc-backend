package com.blanc.market.domain.cart.dto.response;

import com.blanc.market.domain.cart.entity.Cart;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartResponse {
    private Long cartId;
    private Long productId;
    private int productQuantity;

    public static CartResponse from(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getProduct().getId(),
                cart.getProductQuantity()
        );
    }
}
