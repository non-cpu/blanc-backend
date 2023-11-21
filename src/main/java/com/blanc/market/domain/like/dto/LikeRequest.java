package com.blanc.market.domain.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeRequest {
    private Long userId;
    private Long productId;
}
