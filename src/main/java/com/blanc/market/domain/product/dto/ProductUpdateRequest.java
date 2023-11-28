package com.blanc.market.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductUpdateRequest {
    @NotBlank @Size(min = 1, max = 20, message = "test")
    private String name;
    private Integer price;
    private String imageUrl;
    private String description;
}
