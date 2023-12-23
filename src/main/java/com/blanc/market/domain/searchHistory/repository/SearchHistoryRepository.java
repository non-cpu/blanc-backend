package com.blanc.market.domain.searchHistory.repository;

import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.domain.searchHistory.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    Long countByProduct(Product product);

}
