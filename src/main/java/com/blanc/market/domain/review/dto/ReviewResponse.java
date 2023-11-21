package com.blanc.market.domain.review.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String content;
    private float rating;
    private Long productId;
}
