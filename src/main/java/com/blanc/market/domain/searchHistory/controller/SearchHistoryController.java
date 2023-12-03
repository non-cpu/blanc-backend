package com.blanc.market.domain.searchHistory.controller;

import com.blanc.market.domain.searchHistory.dto.SearchHistoryResponse;
import com.blanc.market.domain.searchHistory.service.SearchHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/searchHistory")
@Tag(name = "Search History", description = "Search History API Document")
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    @GetMapping("/{product_id}")
    public ResponseEntity<?> getCount(@PathVariable(value = "product_id") Long productId){
        SearchHistoryResponse response = searchHistoryService.getCount(productId);
        return ResponseEntity.ok(response);
    }
}
