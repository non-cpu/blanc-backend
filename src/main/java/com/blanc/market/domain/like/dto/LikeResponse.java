package com.blanc.market.domain.like.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeResponse {
    private Long id;
    private Long userId;
    private Long productId;
}
