package com.blanc.market.domain.searchHistory.dto;

import com.blanc.market.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchHistoryDto {
    private Long id;

    private Product product;
}
