package com.blanc.market.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderRequest {
    private Long userId;
    private List<OrderProductRequest> products = new ArrayList<>();


}
