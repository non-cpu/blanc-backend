package com.blanc.market.domain.review.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ReviewRequest {
    private String content;
    private float rating;
    private Long productId;
}
