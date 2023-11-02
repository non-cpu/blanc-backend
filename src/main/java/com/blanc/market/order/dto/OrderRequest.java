package com.blanc.market.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderRequest {
    private Long userId;
    private Long productId;
    private int orderprice;
    private int count;
}
