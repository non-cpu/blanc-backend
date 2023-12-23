package com.blanc.market.domain.searchHistory.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchHistoryResponse {
    String productName;
    Long count;
}
