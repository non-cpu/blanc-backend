package com.blanc.market.domain.searchHistory.entity;

import com.blanc.market.domain.product.entity.Product;
import com.blanc.market.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class SearchHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_history_id")
    private Long id;

    @OneToOne(mappedBy = "searchHistory")
    private Product product;

    private int searchCount = 0;
}
