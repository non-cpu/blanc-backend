package com.blanc.market.domain.searchHistory.service;

import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.product.repository.ProductRepository;
import com.blanc.market.domain.searchHistory.dto.SearchHistoryDto;
import com.blanc.market.domain.searchHistory.dto.SearchHistoryResponse;
import com.blanc.market.domain.searchHistory.entity.SearchHistory;
import com.blanc.market.domain.searchHistory.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class SearchHistoryService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final ProductRepository productRepository;

    public SearchHistoryResponse getCount(Long productId){
        Product product = productRepository.findById(productId).orElseThrow();
        Long count = searchHistoryRepository.countByProduct(product);

        SearchHistoryResponse searchHistoryResponse = SearchHistoryResponse.builder()
                .productName(product.getName())
                .count(count)
                .build();

        return searchHistoryResponse;
    }
}
