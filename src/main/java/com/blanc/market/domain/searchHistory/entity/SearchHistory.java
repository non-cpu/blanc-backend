package com.blanc.market.domain.searchHistory.entity;

import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_history_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

//    private int searchCount = 0;
}
