package com.blanc.market.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderProductRequest {
    private Long productId;
    private int quantity;
}
