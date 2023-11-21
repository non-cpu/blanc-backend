package com.blanc.market.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequest {
    @NotNull @NotBlank @Size(min = 1, max = 20, message = "test")
    private String name;
    private int price;
    private String description;
}
