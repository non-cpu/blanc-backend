package com.blanc.market.domain.product.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private double price;
    private String description;
}
